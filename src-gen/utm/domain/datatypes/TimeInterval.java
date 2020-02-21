package utm.domain.datatypes;

public class TimeInterval {
	
	private Time from;
	private Time to;
	private boolean inclusiveFrom;
	private boolean inclusiveTo;
	
	public TimeInterval(Time from, Time to, boolean inclusiveFrom, boolean inclusiveTo) {
		this.from = from;
		this.to = to;
		this.inclusiveFrom = inclusiveFrom;
		this.inclusiveTo = inclusiveTo;
	}
	
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

