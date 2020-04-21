package utm.domain.action_executors;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import utm.domain.datatypes.Drone;
import utm.domain.datatypes.NavigationPoint;
import utm.domain.datatypes.Time;

public class Path implements Comparable<Path> {
	
	private Drone drone;
	private Time startTime;
	private List<Pair<NavigationPoint, Duration>> trajectory;
	
	public Path(Drone drone, Time startTime) {
		trajectory = new ArrayList<>();
		this.startTime = startTime;
		this.drone = drone;
	}
	
	public void add(NavigationPoint navigationPoint, Duration duration) {
		trajectory.add(new ImmutablePair<>(navigationPoint, duration));
	}
	
	public NavigationPoint getFirst() {
		return trajectory.get(0).getKey();
	}
	
	public NavigationPoint getLast() {
		return trajectory.get(trajectory.size() - 1).getKey();
	}
	
	public List<NavigationPoint> getNavigationPoints() {
		return trajectory.stream()
				.map(pair -> pair.getKey())
				.collect(Collectors.toList());
	}
	
	public Time getStartTime() {
		return startTime;
	}
	
	public Time getEndTime() {
		return new Time(startTime.time.plus(getTotalDuration()));
	}
	
	public Duration getTotalDuration() {
		return trajectory.stream()
				.map(pair -> pair.getValue())
				.reduce(Duration.ZERO, (accumulator, current) -> accumulator.plus(current));
	}
	
	public Drone getDrone() {
		return drone;
	}
	
	@Override
	public int compareTo(Path other) {
		return getTotalDuration().compareTo(other.getTotalDuration());
	}
	
}
