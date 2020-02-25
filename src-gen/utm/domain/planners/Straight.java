package utm.domain.planners;

import java.util.ArrayList;
import java.util.List;

import utm.domain.datatypes.Area;
import utm.domain.datatypes.NavigationPoint;

public class Straight implements IBinaryPlanner {
	
	@Override
	public List<NavigationPoint> plan(NavigationPoint start, NavigationPoint end) {
		List<NavigationPoint> result = new ArrayList<>();
		result.add(start);
		result.add(end);
		return result;
	}
	
	@Override
	public List<NavigationPoint> plan(NavigationPoint start, Area end) {
		return plan(start, end.getBoundingBox().get(0)); // TODO: util method for selecting point in Area closest to NavPoint
	}
	
	@Override
	public List<NavigationPoint> plan(NavigationPoint start, List<NavigationPoint> end) {
		List<NavigationPoint> result = plan(start, end.get(0));
		result.addAll(end);
		return result;
	}
	
	
	@Override
	public List<NavigationPoint> plan(Area start, Area end) {
		return plan(start.getBoundingBox().get(start.getBoundingBox().size() - 1), end.getBoundingBox().get(0)); // TODO: util method for selecting point in Area closest to Area
	}
	
	@Override
	public List<NavigationPoint> plan(Area start, NavigationPoint end) {
		return plan(start.getBoundingBox().get(start.getBoundingBox().size() - 1), end); // TODO: util method for selecting point in Area closest to NavPoint
	}
	
	@Override
	public List<NavigationPoint> plan(Area start, List<NavigationPoint> end) {
		// possibly reduces to method above
		List<NavigationPoint> result = plan(start.getBoundingBox().get(start.getBoundingBox().size() - 1), end.get(0)); // TODO: util method for selecting point in Area closest to NavPoint
		result.addAll(end);
		return result;
	}
	
	
	@Override
	public List<NavigationPoint> plan(List<NavigationPoint> start, List<NavigationPoint> end) {
		List<NavigationPoint> result = start;
		result.addAll(plan(start.get(start.size() - 1), end.get(0)));
		result.addAll(end);
		return result;
	}
	
	@Override
	public List<NavigationPoint> plan(List<NavigationPoint> start, NavigationPoint end) {
		List<NavigationPoint> result = start;
		result.addAll(plan(start.get(start.size() - 1), end));
		return result;
	}
	
	@Override
	public List<NavigationPoint> plan(List<NavigationPoint> start, Area end) {
		// possibly reduces to method above
		List<NavigationPoint> result = start;
		result.addAll(plan(start.get(start.size() - 1), end.getBoundingBox().get(0))); // TODO: util method for selecting point in Area closest to NavPoint
		return result;
	}
	
}
