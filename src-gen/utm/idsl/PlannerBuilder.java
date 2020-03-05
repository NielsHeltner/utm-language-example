package utm.idsl;

import java.util.List;

import utm.idsl.metamodel.PlanComponent;
import utm.idsl.metamodel.StraightPlan;
import utm.idsl.metamodel.CoverPlan;
import utm.idsl.metamodel.EachPlan;
import utm.idsl.metamodel.NavigationPointPlan;
import utm.idsl.metamodel.AreaPlan;
import utm.domain.datatypes.NavigationPoint;
import utm.domain.datatypes.Area;

public abstract class PlannerBuilder {
	
	protected abstract boolean verifyConstraints();
	
	protected abstract PlanComponent buildPlan();
	
	public PlanComponent getPlan() {
		if (verifyConstraints()) {
			return buildPlan();
		}
		else {
			return null;
		}
	}
	
	public PlanComponent straight(PlanComponent from, PlanComponent to) {
		return new StraightPlan(from, to);
	}
	
	public PlanComponent cover(PlanComponent target) {
		return new CoverPlan(target);
	}
	
	public PlanComponent navigationPoint(NavigationPoint navigationPoint) {
		return new NavigationPointPlan(navigationPoint);
	}
	
	public PlanComponent area(Area area) {
		return new AreaPlan(area);
	}
	
	public PlanComponent area(List<Area> areas) {
		return null;
	}
	
	public PlanComponent each(List<Area> areas) {
		return new EachPlan(areas);
	}
	
}
