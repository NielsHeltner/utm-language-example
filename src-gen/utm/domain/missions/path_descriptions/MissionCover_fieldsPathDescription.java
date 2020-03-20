package utm.domain.missions.path_descriptions;

import utm.idsl.PathDescriptionBuilder;
import utm.domain.missions.MissionCover_fields;

public class MissionCover_fieldsPathDescription extends PathDescriptionBuilder {
	
	private MissionCover_fields mission;
	
	public MissionCover_fieldsPathDescription(MissionCover_fields mission) {
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
