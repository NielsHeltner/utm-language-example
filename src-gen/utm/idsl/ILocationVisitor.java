package utm.idsl;

import utm.idsl.metamodel.NavigationPointLocation;
import utm.idsl.metamodel.AreaLocation;

public interface ILocationVisitor {
	
	void visit(NavigationPointLocation navigationPointLocation);
	
	void visit(AreaLocation areaLocation);
	
}
