package utm.domain.planners;

import java.util.ArrayList;
import java.util.List;

import utm.idsl.metamodel.AreaPlan;
import utm.idsl.metamodel.NavigationPointPlan;

public class Straight implements IBinaryPlanner {
	
	@Override
	public CompositePlan plan(NavigationPointPlan from, NavigationPointPlan to) {
		List<NavigationPoint> result = new ArrayList<>();
		result.add(from);
		result.add(to);
		return result;
	}
	
	@Override
	public CompositePlan plan(NavigationPointPlan from, AreaPlan to) {
		return plan(from, to.getBoundingBox().get(0)); // TODO: util method for selecting point in Area closest to NavPoint
	}
	
	@Override
	public CompositePlan plan(NavigationPointPlan from, CompositePlan to) {
		List<NavigationPoint> result = plan(from, to.get(0));
		result.addAll(to);
		return result;
	}
	
	
	@Override
	public CompositePlan plan(AreaPlan from, AreaPlan to) {
		return plan(from.getBoundingBox().get(from.getBoundingBox().size() - 1), to.getBoundingBox().get(0)); // TODO: util method for selecting point in Area closest to Area
	}
	
	@Override
	public CompositePlan plan(AreaPlan from, NavigationPointPlan to) {
		return plan(from.getBoundingBox().get(from.getBoundingBox().size() - 1), to); // TODO: util method for selecting point in Area closest to NavPoint
	}
	
	@Override
	public CompositePlan plan(AreaPlan from, CompositePlan to) {
		// possibly reduces to method above
		List<NavigationPoint> result = plan(from.getBoundingBox().get(from.getBoundingBox().size() - 1), to.get(0)); // TODO: util method for selecting point in Area closest to NavPoint
		result.addAll(to);
		return result;
	}
	
	
	@Override
	public CompositePlan plan(CompositePlan from, CompositePlan to) {
		List<NavigationPoint> result = from;
		result.addAll(plan(from.get(from.size() - 1), to.get(0)));
		result.addAll(to);
		return result;
	}
	
	@Override
	public CompositePlan plan(CompositePlan from, NavigationPointPlan to) {
		List<NavigationPoint> result = from;
		result.addAll(plan(from.get(from.size() - 1), to));
		return result;
	}
	
	@Override
	public CompositePlan plan(CompositePlan from, AreaPlan to) {
		// possibly reduces to method above
		List<NavigationPoint> result = from;
		result.addAll(plan(from.get(from.size() - 1), to.getBoundingBox().get(0))); // TODO: util method for selecting point in Area closest to NavPoint
		return result;
	}
	
}
