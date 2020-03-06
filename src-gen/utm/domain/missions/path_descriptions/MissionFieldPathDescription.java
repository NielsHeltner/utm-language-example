package utm.domain.missions.path_descriptions;

import utm.idsl.PathDescriptionBuilder;
import utm.domain.missions.MissionField;

public class MissionFieldPathDescription extends PathDescriptionBuilder {
	
	private MissionField mission;
	
	public MissionFieldPathDescription(MissionField mission) {
		this.mission = mission;
	}
	
	@Override
	protected boolean verifyConstraints() {
		return true;
	}
	
	@Override
	protected void buildPathDescription() {
		straight(navigationPoint(mission.getStart()), navigationPoint(mission.getPoint1())).
		straight(navigationPoint(mission.getPoint1()), navigationPoint(mission.getPoint2()), area(mission.getZone()));
	}
	
}
