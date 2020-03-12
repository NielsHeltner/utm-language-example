package utm.domain.missions;

import java.util.List;
import utm.domain.datatypes.NavigationPoint;

public class MissionStraight_line {
	
	public NavigationPoint start;
	public NavigationPoint end;

	public MissionStraight_line() {}
	
	public NavigationPoint getStart() {
		return this.start;
	}
	
	public NavigationPoint getEnd() {
		return this.end;
	}
	
}

