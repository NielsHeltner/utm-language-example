package utm.idsl.metamodel;

import utm.idsl.Visitor;

public class CoverPlan implements PlanComponent {
	
	private PlanComponent target;
	
	public CoverPlan(PlanComponent target) {
		this.target = target;
	}
	
	@Override
	public PlanComponent accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	public PlanComponent getTarget() {
		return this.target;
	}
	
}
