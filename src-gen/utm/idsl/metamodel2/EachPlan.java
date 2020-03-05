package utm.idsl.metamodel2;

import java.util.List;
import utm.idsl.Visitor;


public class EachPlan implements Planner {
	
	private List<AreaPlan> areas;
	
	public EachPlan(List<AreaPlan> areas) {
		this.areas = areas;
	}
	
	public List<AreaPlan> getAreas() {
		return this.areas;
	}
	
	@Override
	public Planner accept(Visitor visitor) {
		return visitor.visit(this);
	}
}

