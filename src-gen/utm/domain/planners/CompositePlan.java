package utm.domain.planners;

import java.util.ArrayList;
import java.util.List;
import utm.idsl.Visitor;
import utm.idsl.metamodel.PlanComponent;

public class CompositePlan implements PlanComponent {
	
	private List<PlanComponent> plan;
	
	public CompositePlan() {
		this.plan = new ArrayList<>();
	}
	
	@Override
	public PlanComponent accept(Visitor visitor) {
		return this;
	}
	
	public void add(PlanComponent... planComponents) {
		for (utm.idsl.metamodel.PlanComponent planComponent : planComponents) {
			this.plan.add(planComponent);
		}
	}
	
	public PlanComponent getHead() {
		return this.plan.get(0);
	}
	
	public PlanComponent getLast() {
		return this.plan.get(this.plan.size() - 1);
	}
	
	public List<PlanComponent> getPlan() {
		return this.plan;
	}
	
}
