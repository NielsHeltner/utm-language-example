package utm.domain.datatypes;

import utm.domain.datatypes.visitors.LocationVisitor;

public class NavigationPoint implements Location {
	
	public double lat;
	public double lon;
	
	public NavigationPoint() {}
	
	public NavigationPoint(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
	@Override
	public void accept(LocationVisitor locationVisitor) {
		locationVisitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "(" + lat + ", " + lon + ")";
	}
	
}
