package utm.domain.datatypes.visitors;

import utm.domain.datatypes.NavigationPoint;
import utm.domain.datatypes.Area;

public interface LocationVisitor {
	
	void visit(NavigationPoint navigationPoint);
	
	void visit(Area area);
	
}
