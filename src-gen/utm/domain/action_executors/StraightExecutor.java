package utm.domain.action_executors;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;

import utm.domain.ActionExecutorManager;
import utm.domain.datatypes.Area;
import utm.domain.datatypes.NavigationPoint;
import utm.domain.datatypes.visitors.LocationVisitor;
import utm.dsl.metamodel.Straight;

public class StraightExecutor extends AbstractActionExecutor implements LocationVisitor {
	
	private Straight straight;
	private DefaultUndirectedWeightedGraph<Coordinate, DefaultWeightedEdge> graph;
	private List<Coordinate> coordinates; // List to keep the order of points to visit
	private List<Polygon> noFlyZonesAsPolygons;
	
	public StraightExecutor(ActionExecutorManager context, Straight straight, List<Path> paths) {
		super(context, straight, paths);
		this.straight = straight;
	}
	
	@Override
	public void execute() {
		noFlyZonesAsPolygons = context.getNoFlyZones().stream()
				.map(noFlyZone -> areaToPolygon(noFlyZone))
				.collect(Collectors.toList());
		
		for (Path path : paths) {
			graph = initializeGraph(noFlyZonesAsPolygons);
			
			coordinates = new ArrayList<>(straight.getLocations().size() + 1);
			if (path.getLast() != null && !path.getLast().equals(straight.getLocations().get(0))) {
				path.getLast().accept(this);
			}
			straight.getLocations().forEach(location -> location.accept(this));
			
			graph = createVisibilityGraph(graph, noFlyZonesAsPolygons);
			
			List<Coordinate> shortestPath = new ArrayList<>();
			coordinates.stream().reduce(coordinates.get(0), (current, next) -> {
				shortestPath.addAll(getShortestPath(graph, current, next));
				return next;
			});
			System.out.println("Shortest path: " + shortestPath);
			
			shortestPath.stream().reduce(shortestPath.get(0), (current, next) -> {
				if (!coordinateToNavigationPoint(next).equals(path.getLast())) {
					path.add(coordinateToNavigationPoint(next), getTimeBetween(current, next));
				}
				return next;
			});
		}
	}
	
	private Duration getTimeBetween(Coordinate a, Coordinate b) {
		// https://stackoverflow.com/questions/639695/how-to-convert-latitude-or-longitude-to-meters
		// One movement in lat lon = 111km
		// We can move about 11 meters per second -> 0.0001 lat lon
		
		double speed = 0.0001; // meters per second
		double distance = a.distance(b);
		Double duration = Double.valueOf(distance / speed);
		
		return Duration.ofSeconds(duration.longValue());
	}
	
	/**
	 * Creates and returns an undirected, weighted non-simple graph, in which multiple (parallel) edges between any two vertices are not permitted, but loops are.
	 * The graph contains all the supplied polygons as vertices, but does not contain any edges.
	 */
	private DefaultUndirectedWeightedGraph<Coordinate, DefaultWeightedEdge> initializeGraph(List<Polygon> noFlyZones) {
		Coordinate[] noFlyZonesVertices = noFlyZones.stream()
				.flatMap(noFlyZone -> Arrays.stream(noFlyZone.getCoordinates()))
				.toArray(Coordinate[]::new);
		
		return DefaultUndirectedWeightedGraph.<Coordinate, DefaultWeightedEdge>createBuilder(DefaultWeightedEdge.class)
			.addVertices(noFlyZonesVertices)
			.build();
	}
	
	/**
	 * Calculates a visibility graph based on the provided graph. The visibility graph consists of edges between any two vertices that can 'see' each other.
	 */
	private DefaultUndirectedWeightedGraph<Coordinate, DefaultWeightedEdge> createVisibilityGraph(DefaultUndirectedWeightedGraph<Coordinate, DefaultWeightedEdge> graph, List<Polygon> noFlyZones) {
		for (Coordinate current : graph.vertexSet()) {
			for (Coordinate target : graph.vertexSet()) {
				if (!current.equals2D(target)) {
					LineString lineOfSight = new GeometryFactory().createLineString(new Coordinate[] {current, target});
					if (!graph.containsEdge(current, target) && !collision(lineOfSight, noFlyZones)) {
						DefaultWeightedEdge edge = graph.addEdge(current, target);
						graph.setEdgeWeight(edge, lineOfSight.getLength());
					}
				}
			}
		}
		return graph;
	}
	
	private boolean collision(LineString path, List<Polygon> noFlyZones) {
		return noFlyZones.stream()
				.anyMatch((noFlyZone) -> path.crosses(noFlyZone) || path.within(noFlyZone));
	}
	
	private List<Coordinate> getShortestPath(DefaultUndirectedWeightedGraph<Coordinate, DefaultWeightedEdge> graph, Coordinate source, Coordinate sink) {
		DijkstraShortestPath<Coordinate, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graph);
		return dijkstra.getPath(source, sink).getVertexList();
	}
	
	private Polygon areaToPolygon(Area area) {
		Coordinate[] coordinates = area.boundingBox.stream()
				.map(point -> navigationPointToCoordinate(point))
				.toArray(Coordinate[]::new);
		return new GeometryFactory().createPolygon(coordinates);
	}
	
	private NavigationPoint coordinateToNavigationPoint(Coordinate coordinate) {
		return new NavigationPoint(coordinate.getX(), coordinate.getY());
	}
	
	private Coordinate navigationPointToCoordinate(NavigationPoint navigationPoint) {
		return new Coordinate(navigationPoint.lat, navigationPoint.lon);
	}
	
	@Override
	public void visit(NavigationPoint navigationPoint) {
		Coordinate coordinate = navigationPointToCoordinate(navigationPoint);
		coordinates.add(coordinate);
		graph.addVertex(coordinate);
	}
	
	@Override
	public void visit(Area area) {
		
	}
	
}
