package utm.domain.datatypes;

import java.util.Objects;
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
	
	@Override
    public int hashCode() {
        return Objects.hash(lat, lon);
    }
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NavigationPoint navigationPoint = (NavigationPoint) o;
        return Double.compare(navigationPoint.lat, lat) == 0 &&
                Double.compare(navigationPoint.lon, lon) == 0;
    }
	
}
