package utm.domain.action_executors;

import java.time.Duration;
import java.util.List;

import utm.domain.datatypes.Time;

public class PathCollection implements Comparable<PathCollection> {
	
	private List<Path> paths;
	
	public PathCollection(List<Path> paths) {
		this.paths = paths;
	}
	
	public List<Path> getPaths() {
		return paths;
	}
	
	public Time getEarliestStartTime() {
		return paths.stream()
			.map(path -> path.getStartTime())
			.min(Time::compareTo)
			.get();
	}
	
	public Time getLatestEndTime() {
		return paths.stream()
			.map(path -> path.getEndTime())
			.max(Time::compareTo)
			.get();
	}
	
	public Duration getLongestDuration() {
		return this.getEarliestStartTime().between(this.getLatestEndTime());
	}
	
	@Override
	public int compareTo(PathCollection other) {
		int comparison = this.getLongestDuration().compareTo(other.getLongestDuration());
		if (comparison == 0) {
			return this.paths.size() - (other.paths.size());
		}
		else {
			return comparison;
		}
	}
	
}
