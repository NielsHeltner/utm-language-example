package utm.domain.missions.planners;

import java.util.ArrayList;
import java.util.List;

import utm.domain.datatypes.NavigationPoint;

public abstract class AbstractPlanner {
	
	protected abstract boolean verifyConstraints();
	
	protected abstract List<NavigationPoint> createPlan();
	
	public List<NavigationPoint> getPlan() {
		if (verifyConstraints()) {
			return createPlan();
		}
		else {
			return new ArrayList<>();
		}
	}
	
}
