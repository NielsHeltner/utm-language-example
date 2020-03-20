package utm.domain.datatypes;

public class NavigationPoint {
	
	public double lat;
	public double lon;
	
	public NavigationPoint() {}
	
	public double getLat() {
		return lat;
	}
	
	public double getLon() {
		return lon;
	}
	
	@Override
	public String toString() {
		return "(" + lat + ", " + lon + ")";
	}
	
}

