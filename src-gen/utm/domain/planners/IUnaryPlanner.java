package utm.domain.planners;

import java.util.List;

import utm.domain.datatypes.Area;
import utm.domain.datatypes.NavigationPoint;

public interface IUnaryPlanner {
	
	List<NavigationPoint> plan(Area area);
	
	List<NavigationPoint> plan(List<Area> areas);
	
}
