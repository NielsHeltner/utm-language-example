package utm.domain.datatypes;

import java.time.Duration;
import java.time.Instant;

public class Time implements Comparable<Time> {

	public Instant time;
	
	public Time() {}
	
	public Time(Instant time) {
		this.time = time;
	}
	
	public Duration between(Time other) {
		return Duration.between(this.time, other.time);
	}
	
	@Override
	public int compareTo(Time other) {
		return this.time.compareTo(other.time);
	}
	
	@Override
	public String toString() {
		return time.toString();
	}
	
}
