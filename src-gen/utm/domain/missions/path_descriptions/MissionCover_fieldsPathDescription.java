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
		cover(forLoop(new Variable<Area>("a"), area(mission.getAreas())));
		straight(mission.getStart(), new VariableRef<Area>("a"));
	}
	
}
