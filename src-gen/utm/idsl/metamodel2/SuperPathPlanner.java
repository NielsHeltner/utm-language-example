package utm.idsl.metamodel2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import utm.domain.datatypes.NavigationPoint;

public class SuperPathPlanner implements PlannerVisitor {
	
	private List<NavigationPoint> route = new ArrayList<>();
	private NavigationPoint currentPos = null;
	
	public void plan(List<Planner> planners) {
		/**
		 * planners {
		 * 		straight(...)
		 * 		cover(...)
		 * 		straight(...)
		 * }
		 */
		for (Planner planner : planners) {
			planner.
			planner.accept(this);
		}
	}

	@Override
	public void visit(StraightPlan straightPlan) {
		// TODO Auto-generated method stub
		
		List<Waypoint> waypoints = straightPlan.getWaypoints();
		IPlanner straightPlanner;
		route.addAll(straightPlanner.plan(currentPos, waypoints, noFlyZones));
		currentPos = route.getLast();
		
		
		
		
		/*{
			if (currentPos != null) {
				Waypoint head = waypoints.get(0);
			}
		}*/
		
		
		//route.addAll(straightplanner.plan(currentPos, straightPlan.getWaypoints()));
		//currentPos = route.get(route.size() - 1);
	}

	@Override
	public void visit(CoverPlan coverPlan) {
		// TODO Auto-generated method stub
		route.addAll(coverPlanner.plan(coverPlan.getWaypoints()));
	}

}
