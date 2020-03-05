package utm.idsl.metamodel2;

import utm.domain.datatypes.NavigationPoint;
import utm.idsl.Visitor;

public class NavigationPointPlan implements Waypoint {
	
	private NavigationPoint navigationPoint;
	
	public NavigationPointPlan(NavigationPoint navigationPoint) {
		this.navigationPoint = navigationPoint;
	}
	
	public NavigationPoint getNavigationPoint() {
		return this.navigationPoint;
	}

	@Override
	public void accept(WaypointVisitor visitor) {
		visitor.visit(this);
		
	}
	
}
