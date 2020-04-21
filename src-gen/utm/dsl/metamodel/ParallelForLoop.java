package utm.dsl.metamodel;

import java.util.List;

import utm.domain.datatypes.Drone;
import utm.dsl.ActionVisitor;

public class ParallelForLoop<T> extends AbstractForLoop<T> {
	
	public ParallelForLoop(List<T> arguments, List<Drone> drones, List<ForLoopIteration> iterations) {
		super(arguments, drones, iterations);
	}
	
	public ParallelForLoop(List<T> arguments, List<ForLoopIteration> iterations) {
		super(arguments, iterations);
	}
	
	@Override
	public void accept(ActionVisitor actionVisitor) {
		actionVisitor.visit(this);
	}
	
}
