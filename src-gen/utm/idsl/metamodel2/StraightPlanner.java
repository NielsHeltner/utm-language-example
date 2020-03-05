package utm.idsl.metamodel2;

import java.util.ArrayList;
import java.util.List;

import utm.domain.datatypes.Area;
import utm.domain.datatypes.NavigationPoint;

public class StraightPlanner implements IPlanner, WaypointVisitor {
	
	private List<NavigationPoint> route = new ArrayList<>();
	private NavigationPoint currentPos = null;
	private boolean foundArea = false;

	@Override
	public List<NavigationPoint> plan(NavigationPoint currentPos, List<Waypoint> waypoints, List<Area> noFlyZones) {
		this.currentPos = currentPos;
		for (Waypoint waypoint : waypoints) {
			//waypoint.accept(this);
			waypoint.
		}
		return route;
	}

	@Override
	public void visit(NavigationPointPlan navigationPointPlan) {
		if (foundArea)
		
		NavigationPoint p = navigationPointPlan.getNavigationPoint();
		if (collides(currentPos, p))
		route.add(p);
		currentPos = navigationPointPlan.getNavigationPoint();
	}

	@Override
	public void visit(AreaPlan areaPlan) {
		foundArea = true;
		
		
		NavigationPoint p = findClosestNavPoint(areaPlan.getArea(), currentPos);
		route.add(p);
		currentPos = p;
	}

	@Override
	public void visit(EachPlan eachPlan) {
		eachPlan.getAreas()
		
	}

}
