package utm.domain.datatypes;

public class NavigationPoint {
	
	private double lat;
	private double lon;
	
	public NavigationPoint(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
	public double getLat() {
		return lat;
	}
	
	public double getLon() {
		return lon;
	}
	
}

