package utm.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utm.domain.datatypes.Area;
import utm.domain.datatypes.NavigationPoint;

import utm.idsl.PathDescriptionBuilder;
import utm.idsl.metamodel.MetaModel;

public class MissionManager {
	
	private static MissionManager instance;
	private List<Area> noFlyZones; // should probably be a Set
	private Map<MetaModel, List<NavigationPoint>> paths;
	private UniFly uniFly;
	
	private MissionManager() {
		noFlyZones = new ArrayList<>();
		paths = new HashMap<>();
		uniFly = new UniFly();
	}
	
	public static MissionManager getInstance() {
		if (instance == null) {
  			instance = new MissionManager();
  		}
  		return instance;
	}
	
	private List<NavigationPoint> plan(PathPlanner pathPlanner, MetaModel pathDescription) {
		List<NavigationPoint> path = pathPlanner.plan(pathDescription, noFlyZones);
		paths.put(pathDescription, path);
		return path;
	}
	
	public void onPostMission(PathDescriptionBuilder pathDescriptionBuilder, PathPlanner pathPlanner) {
		MetaModel pathDescription = pathDescriptionBuilder.getPathDescriptions();
		
		if (pathDescription == null) {
			// Constraints violated
		}
		
		// Calculate and save path
		List<NavigationPoint> path = plan(pathPlanner, pathDescription);
		
		uniFly.postUasoperation(path);
		
		// upload path to UniFly
	}
	
	public void onUpdateNoFlyZones(Area newNoFlyZone, PathPlanner pathPlanner) {
		noFlyZones.add(newNoFlyZone);
		uniFly.postNoFlyZone(newNoFlyZone);
  			
		for (Map.Entry<MetaModel, List<NavigationPoint>> entry : paths.entrySet()) {
			List<NavigationPoint> path = plan(pathPlanner, entry.getKey());
			// cancel by POST /api/uasoperations/<operationUuid>/cancellation
			uniFly.postUasoperation(path);
			
			// check if new path is different from old path
			// if it is, upload new path to UniFly and remove old path
			// if not, don't do anything
		}
	}
}
