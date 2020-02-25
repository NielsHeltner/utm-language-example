package utm.domain.missions.planners;

import java.util.List;

import utm.domain.datatypes.NavigationPoint;
import utm.domain.missions.MissionFollow_vehicle;
import utm.domain.planners.Straight;
import utm.domain.planners.Cover;

public class MissionFollow_vehiclePlanner extends AbstractPlanner {
	
	private MissionFollow_vehicle mission;
	private Straight planner = new Straight();
	private Cover cover = new Cover(planner);
	
	public MissionFollow_vehiclePlanner(MissionFollow_vehicle mission) {
		this.mission = mission;
	}
	
	@Override
	public boolean verifyConstraints() {
		return true;
	}
	
	@Override
	public List<NavigationPoint> createPlan() {
		return planner.plan(planner.plan(planner.plan(planner.plan(mission.getStart(), mission.getPoint1()), cover.plan(mission.getZone())), mission.getPoint2()), mission.getEnd());
	}
	
	
	/*
	public List<NavigationPoint> createPlanBuilder(MissionFollow_vehicle mission) {
		straight(start).straight(point1).cover(zone).straight(point2).
		straight(end);
	}
	*/
	
}
