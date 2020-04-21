package utm.dsl.metamodel;

import java.util.List;

import utm.domain.datatypes.Drone;
import utm.dsl.ActionVisitor;

public abstract class AbstractForLoop<T> extends AbstractAction {
	
	protected List<T> arguments;
	protected List<ForLoopIteration> iterations;
	
	public AbstractForLoop(List<T> arguments, List<Drone> drones, List<ForLoopIteration> iterations) {
		super(drones);
		this.arguments = arguments;
		this.iterations = iterations;
	}
	
	public AbstractForLoop(List<T> arguments, List<ForLoopIteration> iterations) {
		this(arguments, null, iterations);
	}
	
	@Override
	public abstract void accept(ActionVisitor actionVisitor);
	
	public List<T> getArguments() {
		return arguments;
	}
	
	public List<ForLoopIteration> getIterations() {
		return iterations;
	}
	
}
