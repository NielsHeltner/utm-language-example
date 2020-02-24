package utm.domain.planners;

import java.util.ArrayList;
import java.util.List;

import utm.domain.datatypes.Area;
import utm.domain.datatypes.NavigationPoint;

public abstract class SuperPlanner {
	
	protected List<NavigationPoint> path = new ArrayList<>();
	private NavigationPoint start;
	protected Straight straightPlanner;
	//private UniFly unifly;
	
	
	public SuperPlanner() {}
	
	public abstract boolean verifyConstraints();
	
	
	public abstract List<NavigationPoint> doPlan();
	
	public List<NavigationPoint> getPlan() {
		if (verifyConstraints()) {
			return doPlan();
		}
		else {
			return new ArrayList<>();
		}
	}
	
	public List<NavigationPoint> straight(NavigationPoint start, NavigationPoint end) {
		
	}
	
	public void straight(NavigationPoint start, Area end) {
		straight(start, end.getBoundingBox().get(0));
	}
	
	public void straight(Area start, NavigationPoint end) {
		straight(start, end);
	}
	
	public List<NavigationPoint> straight(List<NavigationPoint> start, NavigationPoint end) {
		List<NavigationPoint> result = start;
		result.addAll(straight(start.get(start.size()-1), end));
		return result;
	}
	
	public void straight(List<NavigationPoint> start, List<NavigationPoint> end) {
		straight(start, end);
	}
	
	public List<NavigationPoint> straight(NavigationPoint start, List<NavigationPoint> end) {
		List<NavigationPoint> result = straight(start, end.get(0));
		result.addAll(end);
		return result;
	}
	
	public void straight(List<NavigationPoint> start, Area end) {
		straight(start, end);
	}
	
	public void straight(Area start, List<NavigationPoint> end) {
		straight(start, end);
	}
	
	public void straight(Area start, Area end) {
		
	}
	
	public List<NavigationPoint> cover(Area area) {
		return null;
	}
	
	
}
