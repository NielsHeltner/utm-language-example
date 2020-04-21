package utm.domain.missions.templates;

import utm.dsl.ActionBuilder;
import utm.domain.missions.MissionBasic;
import utm.domain.UtmDynamic;

public class MissionBasicTemplate extends ActionBuilder {
	
	private UtmDynamic utm;
	private MissionBasic mission;
	
	public MissionBasicTemplate(UtmDynamic utm, MissionBasic mission) {
		this.utm = utm;
		this.mission = mission;
	}
	
	@Override
	protected void buildActions() {
		drone(mission.drones).straight(mission.start).
		drone(mission.a).straight(mission.start).
		drone(mission.a).straight(mission.start).
		parallelForLoop(mission.c, mission.drones, (body0, b, d) -> {
			body0.parallelForLoop(mission.c, mission.drones, (body1, aa, z) -> {
				body1.parallelForLoop(mission.c, (body2, bb) -> {
					body2.drone(z).straight(b);
				});
			});
		}).
		drone(mission.a).straight(mission.start);
	}
	
}
