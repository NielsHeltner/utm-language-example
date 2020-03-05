package utm.domain.planners;

import java.util.List;

import utm.idsl.metamodel.AreaPlan;
import utm.idsl.metamodel.EachPlan;
import utm.idsl.metamodel.PlanComponent;

public interface IUnaryPlanner {
	
	CompositePlan plan(AreaPlan area);
	
	CompositePlan plan(List<AreaPlan> areas);
	
	CompositePlan plan(EachPlan each);

	default CompositePlan plan(PlanComponent target) {
		if (target instanceof AreaPlan) {
			return plan((AreaPlan) target);
		}
		else if (target instanceof EachPlan) {
			return plan((EachPlan) target);
		}
		return null;
		
	}
}
