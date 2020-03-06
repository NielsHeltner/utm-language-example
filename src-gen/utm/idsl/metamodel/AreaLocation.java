package utm.idsl.metamodel;

import utm.domain.datatypes.Area;
import utm.idsl.ILocationVisitor;

public class AreaLocation implements ILocation {
	
	private Area area;
	
	public AreaLocation(Area area) {
		this.area = area;
	}
	
	@Override
	public void accept(ILocationVisitor iLocationVisitor) {
		iLocationVisitor.visit(this);
	}
	
	public Area getArea() {
		return this.area;
	}
	
}
