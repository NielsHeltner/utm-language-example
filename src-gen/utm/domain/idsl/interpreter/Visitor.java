package utm.domain.idsl.interpreter;

import utm.domain.idsl.model.CompositeCoverPath;
import utm.domain.idsl.model.CompositeStraightPath;
import utm.domain.idsl.model.LeafAreaPath;
import utm.domain.idsl.model.LeafNavigationPointPath;
import utm.domain.idsl.model.PathComponent;

public interface Visitor {
	
	PathComponent visit(CompositeStraightPath straight);
	
	PathComponent visit(CompositeCoverPath cover);
	
	PathComponent visit(LeafNavigationPointPath navigationPoint);
	
	PathComponent visit(LeafAreaPath area);

}
