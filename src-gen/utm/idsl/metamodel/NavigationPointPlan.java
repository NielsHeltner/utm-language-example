package utm.idsl.metamodel;

import utm.domain.datatypes.NavigationPoint;
import utm.idsl.Visitor;

public class NavigationPointPlan implements PlanComponent {
	
	private NavigationPoint navigationPoint;
	
	public NavigationPointPlan(NavigationPoint navigationPoint) {
		this.navigationPoint = navigationPoint;
	}
	
	@Override
	public PlanComponent accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	public NavigationPoint getNavigationPoint() {
		return this.navigationPoint;
	}
	
}
