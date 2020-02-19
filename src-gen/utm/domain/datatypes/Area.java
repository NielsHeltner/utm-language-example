package utm.domain.datatypes;

import java.util.List;

public class Area {
	
	private List<NavigationPoint> boundingBox;

	public Area(List<NavigationPoint> boundingBox) {
		this.boundingBox = boundingBox;
	}
	
	public List<NavigationPoint> getBoundingBox() {
		return boundingBox;
	}
	
}

