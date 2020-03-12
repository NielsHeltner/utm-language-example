package utm.domain.planners;

import java.util.List;

import utm.domain.datatypes.Area;
import utm.domain.datatypes.NavigationPoint;
import utm.idsl.metamodel.ILocation;

public interface IPathPlanner {
	
	List<NavigationPoint> plan(NavigationPoint currentPos, List<ILocation> locations, List<Area> noFlightZones);
	
}
