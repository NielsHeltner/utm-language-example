package utm.idsl.metamodel;

import java.util.List;
import utm.idsl.Visitor;


public class EachPlan implements PlanComponent {
	
	private List<AreaPlan> areas;
	
	public EachPlan(List<AreaPlan> areas) {
		this.areas = areas;
	}
	
	public List<AreaPlan> getAreas() {
		return this.areas;
	}
	
	@Override
	public PlanComponent accept(Visitor visitor) {
		return visitor.visit(this);
	}
}

