package utm.idsl.metamodel2;

import java.util.List;

public abstract class Planner {
	
	private List<Waypoint> waypoints;
	
	abstract void accept(PlannerVisitor visitor);
	
}
