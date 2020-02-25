package utm.domain.planners;

import java.util.List;

import utm.domain.datatypes.Area;
import utm.domain.datatypes.NavigationPoint;

public interface IBinaryPlanner {
	
	List<NavigationPoint> plan(NavigationPoint start, NavigationPoint end);
	
	List<NavigationPoint> plan(NavigationPoint start, Area end);
	
	List<NavigationPoint> plan(NavigationPoint start, List<NavigationPoint> end);
	
	List<NavigationPoint> plan(Area start, NavigationPoint end);
	
	List<NavigationPoint> plan(Area start, Area end);
	
	List<NavigationPoint> plan(Area start, List<NavigationPoint> end);
	
	List<NavigationPoint> plan(List<NavigationPoint> start, NavigationPoint end);
	
	List<NavigationPoint> plan(List<NavigationPoint> start, Area end);
	
	List<NavigationPoint> plan(List<NavigationPoint> start, List<NavigationPoint> end);
	
}
