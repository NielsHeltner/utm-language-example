package utm.domain.missions;

import utm.domain.datatypes.NavigationPoint;
import utm.domain.datatypes.Area;

public class MissionFollow_vehicle {
	
	private NavigationPoint start;
	private NavigationPoint point1;
	private Area zone;
	private NavigationPoint point2;
	private NavigationPoint end;

	public MissionFollow_vehicle(NavigationPoint start, NavigationPoint point1, Area zone, NavigationPoint point2, NavigationPoint end) {
		this.start = start;
		this.point1 = point1;
		this.zone = zone;
		this.point2 = point2;
		this.end = end;
	}
	
	public NavigationPoint getStart() {
		return this.start;
	}
	
	public NavigationPoint getPoint1() {
		return this.point1;
	}
	
	public Area getZone() {
		return this.zone;
	}
	
	public NavigationPoint getPoint2() {
		return this.point2;
	}
	
	public NavigationPoint getEnd() {
		return this.end;
	}
	
}

