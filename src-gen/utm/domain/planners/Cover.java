package utm.domain.planners;
	
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utm.domain.datatypes.Area;
import utm.domain.datatypes.NavigationPoint;

public class Cover implements IUnaryPlanner {
	
	private IBinaryPlanner connector;
	
	public Cover(IBinaryPlanner connector) {
		this.connector = connector;
	}
	
	@Override
	public List<NavigationPoint> plan(Area area) { //TODO: maybe second argument, so it knows where to end?
		return area.getBoundingBox(); // TODO: method for calcuating NavPoints covering the area
	}
	
	@Override
	public List<NavigationPoint> plan(List<Area> areas) {
		List<NavigationPoint> result = new ArrayList<>();
		areas.stream().reduce((area1, area2) -> {
			result.addAll(connector.plan(plan(area1), area2));
			return area2;
		});
		return result;
	}
	
}
