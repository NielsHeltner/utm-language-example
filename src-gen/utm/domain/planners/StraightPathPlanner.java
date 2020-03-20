package utm.domain.planners;

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

import utm.idsl.ILocationVisitor;
import utm.idsl.metamodel.ILocation;
import utm.domain.datatypes.Area;
import utm.domain.datatypes.NavigationPoint;
import utm.idsl.metamodel.AreaLocation;
import utm.idsl.metamodel.NavigationPointLocation;

public class StraightPathPlanner implements IPathPlanner, ILocationVisitor {
	
	private List<Coordinate> coordinates;
	
	@Override
	public List<NavigationPoint> plan(NavigationPoint currentPos, List<ILocation> locations, List<Area> noFlyZonesAsAreas) {
		List<Polygon> noFlyZones = noFlyZonesAsAreas.stream()
				.map((noFlyZone) -> areaToPolygon(noFlyZone))
				.collect(Collectors.toList());
		
		coordinates = new ArrayList<>();
		for (ILocation location : locations) {
			location.accept(this);
		}
		
		if (noFlyZones.isEmpty()) {
			// there are no obstacles, so we can just follow the navigation points provided
			System.out.println("Shortest path (no nfz): " + coordinates);
			return coordinates.stream()
					.map((coordinate) -> coordinateToNavigationPoint(coordinate))
					.collect(Collectors.toList());
		}
		
		DefaultUndirectedWeightedGraph<Coordinate, DefaultWeightedEdge> graph = initializeGraph(coordinates, noFlyZones);
		graph = createVisibilityGraph(graph, noFlyZones);
		
		List<Coordinate> shortestPath = getShortestPath(graph, coordinates.get(0), coordinates.get(coordinates.size() - 1));
		System.out.println("Shortest path: " + shortestPath);
		
		return shortestPath.stream()
				.map((coordinate) -> coordinateToNavigationPoint(coordinate))
				.collect(Collectors.toList());
	}
	
	/**
	 * Creates and returns an undirected, weighted non-simple graph, in which multiple (parallel) edges between any two vertices are not permitted, but loops are.
	 * The graph contains all the supplied coordinates as vertices, but does not contain any edges.
	 */
	private DefaultUndirectedWeightedGraph<Coordinate, DefaultWeightedEdge> initializeGraph(List<Coordinate> coordinates, List<Polygon> noFlyZones) {
		Coordinate[] noFlyZonesVertices = noFlyZones.stream()
				.flatMap((noFlyZone) -> Arrays.stream(noFlyZone.getCoordinates()))
				.toArray(Coordinate[]::new);
		
		return DefaultUndirectedWeightedGraph.<Coordinate, DefaultWeightedEdge>createBuilder(DefaultWeightedEdge.class)
			.addVertices(coordinates.stream().toArray(Coordinate[]::new))
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
		Coordinate[] coordinates = area.getBoundingBox().stream()
				.map((point) -> new Coordinate(point.lat, point.lon))
				.toArray(Coordinate[]::new);
		return new GeometryFactory().createPolygon(coordinates);
	}
	
	private NavigationPoint coordinateToNavigationPoint(Coordinate coordinate) {
		NavigationPoint navigationPoint = new NavigationPoint();
		navigationPoint.lat = coordinate.getX();
		navigationPoint.lon = coordinate.getY();
		return navigationPoint;
	}
	
	@Override
	public void visit(NavigationPointLocation navigationPointLocation) {
		coordinates.add(new Coordinate(navigationPointLocation.getNavigationPoint().lat, navigationPointLocation.getNavigationPoint().lon));
	}
	
	@Override
	public void visit(AreaLocation areaLocation) {
		
	}
	
}
