package utm.domain.planners;

import java.util.List;

import utm.domain.PathPlannerManager;
import utm.idsl.metamodel.AreaLocation;
import utm.idsl.metamodel.ForLoop;
import utm.idsl.metamodel.ILocation;
import utm.idsl.metamodel.NavigationPointLocation;

public class CoverPathPlanner extends AbstractPathPlanner {
	
	public CoverPathPlanner(PathPlannerManager context, List<ILocation> locations) {
		super(context, locations);
	}
	
	@Override
	public void visit(NavigationPointLocation navigationPointLocation) {
		
	}
	
	@Override
	public void visit(AreaLocation areaLocation) {
		
	}
	
	@Override
	public void visit(ForLoop forLoop) {
		
	}
	
}
