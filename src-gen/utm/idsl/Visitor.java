package utm.idsl;

import utm.idsl.metamodel.PlanComponent;
import utm.idsl.metamodel.StraightPlan;
import utm.idsl.metamodel.CoverPlan;
import utm.idsl.metamodel.NavigationPointPlan;
import utm.idsl.metamodel.AreaPlan;
import utm.idsl.metamodel.EachPlan;

public interface Visitor {
	
	PlanComponent visit(StraightPlan straightPlan);
	
	PlanComponent visit(CoverPlan coverPlan);
	
	PlanComponent visit(NavigationPointPlan navigationPointPlan);
	
	PlanComponent visit(AreaPlan areaPlan);
	
	PlanComponent visit(EachPlan eachPlan);
	
}
