package utm.idsl.metamodel;

import utm.domain.datatypes.Area;
import utm.idsl.Visitor;

public class AreaPlan implements PlanComponent {
	
	private Area area;
	
	public AreaPlan(Area area) {
		this.area = area;
	}
	
	@Override
	public PlanComponent accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	public Area getArea() {
		return this.area;
	}
	
}
