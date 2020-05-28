package utm.dsl.metamodel;

import java.util.List;
import utm.domain.datatypes.Location;
import utm.domain.datatypes.Drone;
import utm.dsl.ActionVisitor;

public class Straight extends AbstractAction {
	
	private List<? extends Location> locations;
	
	public Straight(List<Drone> drones, List<? extends Location> locations) {
		super(drones);
		this.locations = locations;
	}
	
	public List<? extends Location> getLocations() {
		return locations;
	}
	
	@Override
	public void accept(ActionVisitor actionVisitor) {
		actionVisitor.visit(this);
	}
	
}
