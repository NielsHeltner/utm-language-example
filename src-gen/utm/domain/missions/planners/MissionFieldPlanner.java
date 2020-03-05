package utm.domain.missions.planners;

import java.util.List;

import utm.idsl.PlannerBuilder;
import utm.idsl.metamodel.PlanComponent;
import utm.domain.missions.MissionField;

public class MissionFieldPlanner extends PlannerBuilder {
	
	private MissionField mission;
	
	public MissionFieldPlanner(MissionField mission) {
		this.mission = mission;
	}
	
	@Override
	public boolean verifyConstraints() {
		return true;
	}
	
	@Override
	public PlanComponent buildPlan() {
		return straight(navigationPoint(mission.getStart()), navigationPoint(mission.getPoint1()), navigationPoint(mission.getPoint2())).
		zigzag(navigationPoint(mission.getPoint2()), navigationPoint(mission.getEnd()));
	}
	
}
