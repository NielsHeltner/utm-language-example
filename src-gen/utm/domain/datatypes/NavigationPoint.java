package utm.domain.datatypes;

public class NavigationPoint {
	
	private String lat;
	private String lon;
	
	public NavigationPoint(String lat, String lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
	public String getLat() {
		return lat;
	}
	
	public String getLon() {
		return lon;
	}
	
}

