package utm.domain.missions.planners;

import java.util.List;

import utm.domain.datatypes.NavigationPoint;
import utm.domain.missions.MissionMark;
import utm.domain.planners.Straight;
import utm.domain.planners.Cover;
import utm.domain.planners.Zigzag;

public class MissionMarkPlanner extends AbstractPlanner {
	
	private MissionMark mission;
	private Straight planner = new Straight();
	private Cover cover = new Cover(planner);
	private Zigzag zigzag = new Zigzag();
	
	public MissionMarkPlanner(MissionMark mission) {
		this.mission = mission;
	}
	
	@Override
	public boolean verifyConstraints() {
		return true;
	}
	
	@Override
	public List<NavigationPoint> createPlan() {
		return planner.plan(zigzag.plan(planner.plan(mission.getStart(), mission.getOmraader()), mission.getOmraader()), planner.plan(mission.getStart(), cover.plan(mission.getOmraader())));
	}
	
	
	/*
	public List<NavigationPoint> createPlanBuilder(MissionMark mission) {
		straight(start).straight(omraader).zigzag(omraader).
		straight(start).cover(omraader);
	}
	*/
	
}
