package utm.domain.missions.path_descriptions;

import utm.idsl.PathDescriptionBuilder;
import utm.domain.missions.MissionStraight_line;

public class MissionStraight_linePathDescription extends PathDescriptionBuilder {
	
	private MissionStraight_line mission;
	
	public MissionStraight_linePathDescription(MissionStraight_line mission) {
		this.mission = mission;
	}
	
	@Override
	protected boolean verifyConstraints() {
		return true;
	}
	
	@Override
	protected void buildPathDescription() {
		straight(navigationPoint(mission.getStart()), navigationPoint(mission.getEnd()));
	}
	
}
