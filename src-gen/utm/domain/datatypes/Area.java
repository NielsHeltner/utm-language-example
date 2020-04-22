package utm.domain.datatypes;

import java.util.List;
import utm.domain.datatypes.visitors.LocationVisitor;

public class Area implements Location {
	
	public List<NavigationPoint> boundingBox;
	
	@Override
	public void accept(LocationVisitor locationVisitor) {
		locationVisitor.visit(this);
	}
	
	@Override
	public String toString() {
		return boundingBox.toString();
	}
	
}
