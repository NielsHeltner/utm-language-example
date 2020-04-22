package utm.dsl.metamodel;

import java.util.List;

import utm.dsl.ActionVisitor;
import utm.domain.datatypes.Drone;

public abstract class AbstractAction {
	
	protected List<Drone> drones;
	
	public AbstractAction(List<Drone> drones) {
		this.drones = drones;
	}
	
	public abstract void accept(ActionVisitor actionVisitor);
	
	public List<Drone> getDrones() {
		return drones;
	}
	
}
