package utm.dsl.metamodel;

import java.util.List;

import utm.domain.datatypes.Drone;
import utm.dsl.ActionVisitor;

public class PrioritizedForLoop<T> extends AbstractForLoop<T> {
	
	public PrioritizedForLoop(List<T> arguments, List<Drone> drones, List<ForLoopIteration> iterations) {
		super(arguments, drones, iterations);
	}
	
	public PrioritizedForLoop(List<T> arguments, List<ForLoopIteration> iterations) {
		super(arguments, iterations);
	}
	
	@Override
	public void accept(ActionVisitor actionVisitor) {
		actionVisitor.visit(this);
	}
	
}
