package utm.domain.datatypes;

import java.util.List;

public class Area {
	
	public List<NavigationPoint> boundingBox;

	public Area() {}
	
	public List<NavigationPoint> getBoundingBox() {
		return boundingBox;
	}
	
}

