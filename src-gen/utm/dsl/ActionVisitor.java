package utm.dsl;

import utm.dsl.metamodel.Straight;
import utm.dsl.metamodel.ParallelForLoop;

public interface ActionVisitor {
	
	void visit(Straight straight);
	
	<T> void visit(ParallelForLoop<T> parallelForLoop);
	
}
