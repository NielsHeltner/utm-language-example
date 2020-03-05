package utm.idsl.metamodel2;

import utm.idsl.Visitor;

public class CoverPlan implements Planner {
	
	private Planner target;
	
	public CoverPlan(Planner target) {
		this.target = target;
	}
	
	public Planner getTarget() {
		return this.target;
	}
	
}
