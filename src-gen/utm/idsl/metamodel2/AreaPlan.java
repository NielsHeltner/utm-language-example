package utm.idsl.metamodel2;

import utm.domain.datatypes.Area;

public class AreaPlan implements Waypoint {
	
	private Area area;
	
	public AreaPlan(Area area) {
		this.area = area;
	}
	
	public Area getArea() {
		return this.area;
	}

	@Override
	public void accept(WaypointVisitor visitor) {
		visitor.visit(this);
	}
	
}
