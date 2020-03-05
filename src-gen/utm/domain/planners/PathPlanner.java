package utm.domain.planners;

import utm.idsl.Visitor;
import utm.idsl.metamodel.PlanComponent;
import utm.idsl.metamodel.StraightPlan;
import utm.idsl.metamodel.CoverPlan;
import utm.idsl.metamodel.NavigationPointPlan;
import utm.idsl.metamodel.AreaPlan;
import utm.idsl.metamodel.EachPlan;

public class PathPlanner implements Visitor {
	
	private IBinaryPlanner straightPlanner = new Straight();
	private IUnaryPlanner coverPlanner = new Cover();
	
	@Override
	public PlanComponent visit(StraightPlan straight) {
		PlanComponent from = straight.getFrom().accept(this);
		PlanComponent to = straight.getTo().accept(this);
		
		return straightPlanner.plan(from, to);
	}
	
	@Override
	public PlanComponent visit(CoverPlan cover) {
		PlanComponent target = cover.getTarget().accept(this);
		
		return coverPlanner.plan(target);
	}
	
	@Override
	public PlanComponent visit(NavigationPointPlan navigationPoint) {
		return navigationPoint;
	}
	
	@Override
	public PlanComponent visit(AreaPlan area) {
		return area;
	}
	
	@Override
	public PlanComponent visit(EachPlan each) {
		return each;
	}
	
}
