package utm.domain.action_executors;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import utm.domain.datatypes.Time;

public class PathCollection implements Comparable<PathCollection> {
	
	private List<Path> paths;
	
	public PathCollection(List<Path> paths) {
		this.paths = paths;
	}
	
	public List<Path> getPaths() {
		return paths;
	}
	
	public Optional<Time> getEarliestStartTime() {
		return paths.stream()
			.map(path -> path.getStartTime())
			.min(Time::compareTo);
	}
	
	public Optional<Time> getLatestEndTime() {
		return paths.stream()
			.map(path -> path.getEndTime())
			.max(Time::compareTo);
	}
	
	public Duration getLongestDuration() {
		Optional<Time> earliestStart = this.getEarliestStartTime();
		Optional<Time> latestEnd = this.getLatestEndTime();
		if (earliestStart.isPresent() && latestEnd.isPresent()) {
			return earliestStart.get().between(latestEnd.get());
		}
		else {
			return Duration.ZERO;
		}
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
	
	@Override
	public String toString() {
		return paths.toString();
	}
	
}
