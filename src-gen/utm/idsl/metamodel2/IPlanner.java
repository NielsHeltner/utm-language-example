package utm.idsl.metamodel2;

import java.util.List;

import utm.domain.datatypes.Area;
import utm.domain.datatypes.NavigationPoint;

public interface IPlanner {
	
	List<NavigationPoint> plan(NavigationPoint currentPos, List<Waypoint> waypoints, List<Area> noFlyZones);
}
