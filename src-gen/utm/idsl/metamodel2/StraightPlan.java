package utm.idsl.metamodel2;

import java.util.List;

public class StraightPlan implements Planner {
	
	public StraightPlan(List<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}

	@Override
	public void accept(PlannerVisitor visitor) {
		visitor.visit(this);
	}
	
	public List<Waypoint> getWaypoints() {
		return waypoints;
	}
	
}
