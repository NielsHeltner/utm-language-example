package utm.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import utm.domain.action_executors.PathCollection;
import utm.domain.datatypes.Area;
import utm.dsl.ActionBuilder;
import utm.dsl.metamodel.MetaModel;

public class OperationManager {
	
	private static OperationManager instance;
	private List<Area> noFlyZones; // should probably be a Set
	private Map<MetaModel, PathCollection> paths;
	private Unifly unifly;
	
	private OperationManager() {
		noFlyZones = new ArrayList<>();
		paths = new HashMap<>();
		unifly = new Unifly();
	}
	
	public static OperationManager getInstance() {
		if (instance == null) {
  			instance = new OperationManager();
  		}
  		return instance;
	}
	
	private PathCollection execute(MetaModel actions) {
		TreeSet<PathCollection> candidates = new TreeSet<>();
		for (int i = 0; i < 5; i++) {
			ActionExecutorManager actionExecutorManager = new ActionExecutorManager();
			PathCollection pathCollection = actionExecutorManager.execute(actions, noFlyZones);
			candidates.add(pathCollection);
		}
		
		PathCollection pathCollection = candidates.first();
		paths.put(actions, pathCollection);
		return pathCollection;
	}
	
	public void onCreateOperation(ActionBuilder actionBuilder) throws IOException {
		MetaModel actions = actionBuilder.getMetaModel();
		
		PathCollection pathCollection = execute(actions);
		
		unifly.createUasOperations(pathCollection);
	}
	
	public void onAddNoFlyZone(Area newNoFlyZone, ActionExecutorManager actionExecutorManager) throws IOException {
		noFlyZones.add(newNoFlyZone);
		unifly.createNoFlyZone(newNoFlyZone);
		
		for (Map.Entry<MetaModel, PathCollection> entry : paths.entrySet()) {
			PathCollection pathCollection = execute(entry.getKey());
			// cancel by POST /api/uasoperations/<operationUuid>/cancellation
			unifly.createUasOperations(pathCollection);
		}
	}
	
	public List<Area> getNoFlyZones() {
		return noFlyZones;
	}
	
}
