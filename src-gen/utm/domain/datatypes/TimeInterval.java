package utm.domain.datatypes;

public class TimeInterval {
	
	public Time from;
	public Time to;
	public boolean inclusiveFrom;
	public boolean inclusiveTo;
	
	public TimeInterval() {}
	
	public Time getFrom() {
		return from;
	}
	
	public Time getTo() {
		return to;
	}
	
	public boolean getÍnclusiveFrom() {
		return inclusiveFrom;
	}
	
	public boolean getInclusiveTo() {
		return inclusiveTo;
	}
	
}

