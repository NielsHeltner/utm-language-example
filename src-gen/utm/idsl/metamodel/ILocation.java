package utm.idsl.metamodel;

import java.util.ArrayList;
import java.util.List;
import utm.idsl.ILocationVisitor;

public interface ILocation {
	
	void accept(ILocationVisitor iLocationVisitor);
	
}
