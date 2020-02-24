package utm.domain.missions.planners;

import java.util.ArrayList;
import java.util.List;

import utm.domain.datatypes.Area;
import utm.domain.datatypes.NavigationPoint;
import utm.domain.missions.MissionFollow_vehicle;
import utm.domain.planners.SuperPlanner;
import utm.domain.planners.SuperPlannerFollow;

public class MissionFollow_vehiclePlanner extends SuperPlanner {
	
	private String defaultPlanner = "straight";
	private List<NavigationPoint> dinMor;
	private MissionFollow_vehicle mission;
	
	public MissionFollow_vehiclePlanner(MissionFollow_vehicle mission) {
		this.mission = mission;
	}
	
	public NavigationPoint[] getPlan(MissionFollow_vehicle mission) {
		straight(start).straight(slut);
	}
	
	
	@Override
	public List<NavigationPoint> straight(NavigationPoint start, NavigationPoint end) {
		
		
		return straightPlanner.plan(start, end);
	}
	
	@Override
	public List<NavigationPoint> cover(Area area) {
		return dinMor;
	}
	
	public List<NavigationPoint> getPlanNested(MissionFollow_vehicle mission) {
		this.mission = mission;
		List<NavigationPoint> arg1 = straight(mission.getStart(), mission.getSlut());
		List<NavigationPoint> arg2 = straight(arg1, mission.getSlut());
		arg1.addAll(arg2);
		return arg1;
		
		straight(straight(mission.getStart(), mission.getSlut()), mission.getSlut());
		
		straight.plan(straight.plan(mission.getStart(), mission.getSlut()), mission.getSlut());
		
		
		
		return path;
	}


	@Override
	public boolean verifyConstraints() {
		return true;
	}


	@Override
	public List<NavigationPoint> doPlan() {
		return straight.plan(straight.plan(mission.getStart(), mission.getSlut()), mission.getSlut());
	}
	
	
}

