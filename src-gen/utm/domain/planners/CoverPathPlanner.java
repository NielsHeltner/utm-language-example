package utm.domain.planners;
		
import java.util.ArrayList;
import java.util.List;

import utm.domain.datatypes.Area;
import utm.domain.datatypes.NavigationPoint;
import utm.idsl.metamodel.AreaLocation;
import utm.idsl.metamodel.NavigationPointLocation;

import utm.idsl.metamodel.ILocation;

import utm.idsl.ILocationVisitor;
import utm.domain.planners.IPathPlanner;

public class CoverPathPlanner implements IPathPlanner, ILocationVisitor {
	
	private List<NavigationPoint> path = new ArrayList<>();
	
	@Override
	public List<NavigationPoint> plan(NavigationPoint currentPos, List<ILocation> locations, List<Area> noFlightZones) {
		return path;
	}
	
	@Override
	public void visit(NavigationPointLocation navigationPointLocation) {
		
	}
	
	@Override
	public void visit(AreaLocation areaLocation) {
		
	}
}
