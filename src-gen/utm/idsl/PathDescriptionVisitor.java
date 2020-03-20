package utm.idsl;

import utm.idsl.metamodel.Straight;
import utm.idsl.metamodel.Cover;

public interface PathDescriptionVisitor {
	
	void visit(Straight straight);
	
	void visit(Cover cover);
	
}
