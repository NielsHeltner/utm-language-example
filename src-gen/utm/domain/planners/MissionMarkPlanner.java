package utm.domain.planners;

import utm.domain.datatypes.NavigationPoint;
import utm.domain.missions.MissionMark;

public class MissionMarkPlanner {
	
	private String straight = "straight";
	
	public NavigationPoint[] getPlan(MissionMark mission) {
		straight(start).straight(punkt1).straight(punkt2).
		straight(punkt3).straight(punkt4).
		straight(punkt5).straight(punkt6).straight(punkt7).straight(slut).
		cover(omraader)
	}
	
	public NavigationPoint[] straight(NavigationPoint a, NavigationPoint b) {
		return new NavigationPoint[] {a, b};
	}
	
	
	
	public NavigationPoint[] getPlanNested(MissionMark mission) {
		straight(straight(straight(straight(straight(start, punkt1), punkt2), straight(punkt3, punkt4)), straight(straight(straight(punkt5, punkt6), punkt7), slut)), cover(omraader));
	}
	
	
}
