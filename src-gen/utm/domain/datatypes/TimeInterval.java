package utm.domain.datatypes;

public class TimeInterval {
	
	public Time from;
	public Time to;
	
	public boolean isWithin(Time time) {
		return from.compareTo(time) < 1 && to.compareTo(time) > -1;
	}
	
	@Override
	public String toString() {
		return "[" + from + "; " + to + "]";
	}
	
}
