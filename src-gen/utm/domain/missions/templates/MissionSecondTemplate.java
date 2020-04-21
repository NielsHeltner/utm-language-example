package utm.domain.missions.templates;

import utm.dsl.ActionBuilder;
import utm.domain.missions.MissionSecond;
import utm.domain.UtmDynamic;

public class MissionSecondTemplate extends ActionBuilder {
	
	private UtmDynamic utm;
	private MissionSecond mission;
	
	public MissionSecondTemplate(UtmDynamic utm, MissionSecond mission) {
		this.utm = utm;
		this.mission = mission;
	}
	
	@Override
	protected void buildActions() {
		drone(mission.drones).straight(mission.start);
	}
	
}
