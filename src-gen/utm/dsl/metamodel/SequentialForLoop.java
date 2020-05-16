package utm.dsl.metamodel;

import java.util.List;

import utm.domain.datatypes.Drone;
import utm.dsl.ActionVisitor;

public class SequentialForLoop<T> extends AbstractForLoop<T> {
	
	public SequentialForLoop(List<T> arguments, List<Drone> drones, List<ForLoopIteration> iterations) {
		super(arguments, drones, iterations);
	}
	
	public SequentialForLoop(List<T> arguments, List<ForLoopIteration> iterations) {
		super(arguments, iterations);
	}
	
	@Override
	public void accept(ActionVisitor actionVisitor) {
		actionVisitor.visit(this);
	}
	
}
