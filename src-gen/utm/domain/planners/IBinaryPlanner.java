package utm.domain.planners;

import utm.idsl.metamodel.NavigationPointPlan;
import utm.idsl.metamodel.AreaPlan;
import utm.domain.planners.CompositePlan;
import utm.idsl.metamodel.PlanComponent;

public interface IBinaryPlanner {
	
	CompositePlan plan(NavigationPointPlan from, NavigationPointPlan to);
	
	CompositePlan plan(NavigationPointPlan from, AreaPlan to);
	
	CompositePlan plan(NavigationPointPlan from, CompositePlan to);
	
	CompositePlan plan(AreaPlan from, NavigationPointPlan to);
	
	CompositePlan plan(AreaPlan from, AreaPlan to);
	
	CompositePlan plan(AreaPlan from, CompositePlan to);
	
	CompositePlan plan(CompositePlan from, NavigationPointPlan to);
	
	CompositePlan plan(CompositePlan from, AreaPlan to);
	
	CompositePlan plan(CompositePlan from, CompositePlan to);
	
	
	default CompositePlan plan(PlanComponent first, PlanComponent second) {
		if (first instanceof NavigationPointPlan && second instanceof NavigationPointPlan) {
			return plan((NavigationPointPlan) first, (NavigationPointPlan) second);
		}
		
		if (first instanceof NavigationPointPlan && second instanceof AreaPlan) {
			return plan((NavigationPointPlan) first, (AreaPlan) second);
		}
		
		if (first instanceof NavigationPointPlan && second instanceof CompositePlan) {
			return plan((NavigationPointPlan) first, (CompositePlan) second);
		}
		
		if (first instanceof AreaPlan && second instanceof NavigationPointPlan) {
			return plan((AreaPlan) first, (NavigationPointPlan) second);
		}
		
		if (first instanceof AreaPlan && second instanceof AreaPlan) {
			return plan((AreaPlan) first, (AreaPlan) second);
		}
		
		if (first instanceof AreaPlan && second instanceof CompositePlan) {
			return plan((AreaPlan) first, (CompositePlan) second);
		}
		
		if (first instanceof CompositePlan && second instanceof NavigationPointPlan) {
			return plan((CompositePlan) first, (NavigationPointPlan) second);
		}
		
		if (first instanceof CompositePlan && second instanceof AreaPlan) {
			return plan((CompositePlan) first, (AreaPlan) second);
		}
		
		if (first instanceof CompositePlan && second instanceof CompositePlan) {
			return plan((CompositePlan) first, (CompositePlan) second);
		}
		
		return null;
	}
}
