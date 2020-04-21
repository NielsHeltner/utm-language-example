package utm.domain.datatypes;

import utm.domain.datatypes.visitors.LocationVisitor;

public interface Location {
	
	void accept(LocationVisitor locationVisitor);
	
}
