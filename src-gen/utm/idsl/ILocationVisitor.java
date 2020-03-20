package utm.idsl;

import utm.idsl.metamodel.NavigationPointLocation;
import utm.idsl.metamodel.AreaLocation;
import utm.idsl.metamodel.ForLoop;

public interface ILocationVisitor {
	
	void visit(NavigationPointLocation navigationPointLocation);
	
	void visit(AreaLocation areaLocation);
	
	void visit(ForLoop forLoop);
	
}
