package utm.idsl.metamodel;

import utm.idsl.Visitor;

public class StraightPlan implements PlanComponent {
	
	private PlanComponent from;
	private PlanComponent to;
	
	public StraightPlan(PlanComponent from, PlanComponent to) {
		this.from = from;
		this.to = to;
	}
	
	@Override
	public PlanComponent accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	public PlanComponent getFrom() {
		return this.from;
	}
	
	public PlanComponent getTo() {
		return this.to;
	}
	
}
