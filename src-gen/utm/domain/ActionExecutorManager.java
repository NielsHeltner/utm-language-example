package utm.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Iterables;

import utm.domain.datatypes.Area;
import utm.domain.datatypes.Drone;
import utm.domain.datatypes.NavigationPoint;
import utm.domain.datatypes.Time;
import utm.domain.action_executors.Path;
import utm.domain.action_executors.PathCollection;
import utm.dsl.metamodel.Straight;
import utm.domain.action_executors.StraightExecutor;
import utm.dsl.ActionVisitor;
import utm.dsl.metamodel.MetaModel;
import utm.dsl.metamodel.ActionCollection;
import utm.dsl.metamodel.ForLoopIteration;
import utm.dsl.metamodel.ParallelForLoop;

public class ActionExecutorManager implements ActionVisitor {
	
	private Map<Drone, Path> paths;
	private NavigationPoint currentPosition; // should be moved to Path
	private Time startTime; // should probably be moved to path (maybe kept here to "synchronize" paths upon instantiation)
	private Time currentTime; // same as above
	private DroneManager droneManager;
	private Map<Drone, Drone> resolvedDrones;
	
	private List<Area> noFlyZones;
	
	public ActionExecutorManager() {
		this.paths = new HashMap<>();
		this.resolvedDrones = new HashMap<>();
		this.droneManager = new DroneManager();
	}
	
	public PathCollection execute(MetaModel metaModel, List<Area> noFlyZones) {
		this.noFlyZones = noFlyZones;
		startTime = getRandomStartTime();
		currentTime = startTime;
		
		visitAll(metaModel.getActionCollection());
		
		return new PathCollection(new ArrayList<>(paths.values()));
	}
	
	public Time getRandomStartTime() {
		Instant startTime = Instant.now().plusSeconds((long)(Math.random() * 1200));
		return new Time(startTime);
	}
	
	public void visitAll(ActionCollection visitees) {
		visitees.getActions().forEach(action -> action.accept(this));
	}
	
	public NavigationPoint getCurrentPosition() {
		return currentPosition;
	}
	
	public Drone getMappedDrone(Drone drone) {
		return resolvedDrones.getOrDefault(drone, drone);
	}
	
	public Path getPath() {
		return null;
	}
	
	public Time getStartTime() {
		return startTime;
	}
	
	public Time getCurrentTime() {
		return currentTime;
	}
		
	public List<Area> getNoFlyZones() {
		return Collections.unmodifiableList(noFlyZones);
	}
	
	private List<Path> getPaths(List<Drone> drones) {
		return drones.stream()
				.map(this::getMappedDrone)
				.map(drone -> paths.computeIfAbsent(drone, droneKey -> new Path(droneKey, startTime)))
				.collect(Collectors.toList());
		
		/*return drones.stream()
				.map(this::getMappedDrone)
				.map(drone -> {
					paths.putIfAbsent(drone, new Path(drone, startTime));
					return drone;
				})
				.map(paths::get)
				.collect(Collectors.toList());*/
	}
	
	@Override
	public void visit(Straight straight) {
		List<Path> paths = getPaths(straight.getDrones());
		
		new StraightExecutor(this, straight, paths).execute();
	}
	
	@Override
	public <T> void visit(ParallelForLoop<T> parallelForLoop) {
		// do forloop (should probably not be generated in a loop)
		// Implemented for Parallel
		
		Collections.shuffle(parallelForLoop.getIterations());
		
		if (parallelForLoop.getDrones() != null) {
			List<Drone> droneSubset = droneManager.getAvailableDrones(parallelForLoop.getDrones(), startTime, parallelForLoop.getArguments().size());		
			resolveDrones(droneSubset, parallelForLoop.getIterations());
		}
		
		parallelForLoop.getIterations().forEach(iteration -> visitAll(iteration.getActionCollection()));
	}
	
	private void resolveDrones(List<Drone> droneSubset, List<ForLoopIteration> iterations) {
		Iterator<Drone> droneSubsetIterator = Iterables.cycle(droneSubset).iterator();
		
		iterations.forEach(iteration -> resolvedDrones.put(iteration.getUnresolvedDrone(), droneSubsetIterator.next()));
	}
	
}
