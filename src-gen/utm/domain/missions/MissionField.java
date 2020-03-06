package utm.domain.missions;

import java.util.List;
import utm.domain.datatypes.TimeInterval;
import utm.domain.datatypes.NavigationPoint;
import utm.domain.datatypes.Area;

public class MissionField {
	
	private TimeInterval when;
	private NavigationPoint start;
	private NavigationPoint point1;
	private Area zone;
	private List<Area> zones;
	private NavigationPoint point2;
	private NavigationPoint end;

	public MissionField(TimeInterval when, NavigationPoint start, NavigationPoint point1, Area zone, List<Area> zones, NavigationPoint point2, NavigationPoint end) {
		this.when = when;
		this.start = start;
		this.point1 = point1;
		this.zone = zone;
		this.zones = zones;
		this.point2 = point2;
		this.end = end;
	}
	
	public TimeInterval getWhen() {
		return this.when;
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
	
	public List<Area> getZones() {
		return this.zones;
	}
	
	public NavigationPoint getPoint2() {
		return this.point2;
	}
	
	public NavigationPoint getEnd() {
		return this.end;
	}
	
}

