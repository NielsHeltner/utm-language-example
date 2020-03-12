package utm.domain.missions;

import java.util.List;
import utm.domain.datatypes.TimeInterval;
import utm.domain.datatypes.NavigationPoint;
import utm.domain.datatypes.Area;

public class MissionCover_fields {
	
	public TimeInterval when;
	public NavigationPoint start;
	public NavigationPoint end;
	public List<Area> areas;

	public MissionCover_fields() {}
	
	public TimeInterval getWhen() {
		return this.when;
	}
	
	public NavigationPoint getStart() {
		return this.start;
	}
	
	public NavigationPoint getEnd() {
		return this.end;
	}
	
	public List<Area> getAreas() {
		return this.areas;
	}
	
}

