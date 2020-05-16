package utm.dsl;

import utm.dsl.metamodel.Straight;
import utm.dsl.metamodel.SequentialForLoop;
import utm.dsl.metamodel.PrioritizedForLoop;
import utm.dsl.metamodel.ParallelForLoop;

public interface ActionVisitor {
	
	void visit(Straight straight);
	
	<T> void visit(SequentialForLoop<T> sequentialForLoop);
	
	<T> void visit(PrioritizedForLoop<T> prioritizedForLoop);
	
	<T> void visit(ParallelForLoop<T> parallelForLoop);
	
}
