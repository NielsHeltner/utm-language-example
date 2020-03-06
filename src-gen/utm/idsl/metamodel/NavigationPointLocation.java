package utm.idsl.metamodel;

import utm.domain.datatypes.NavigationPoint;
import utm.idsl.ILocationVisitor;

public class NavigationPointLocation implements ILocation {
	
	private NavigationPoint navigationPoint;
	
	public NavigationPointLocation(NavigationPoint navigationPoint) {
		this.navigationPoint = navigationPoint;
	}
	
	@Override
	public void accept(ILocationVisitor iLocationVisitor) {
		iLocationVisitor.visit(this);
	}
	
	public NavigationPoint getNavigationPoint() {
		return this.navigationPoint;
	}
	
}
