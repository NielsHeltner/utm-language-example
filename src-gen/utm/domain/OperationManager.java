package utm.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import utm.domain.action_executors.PathCollection;
import utm.domain.datatypes.Area;
import utm.dsl.metamodel.OperationTemplate;
import utm.domain.external_utm.ExternalUtm;
import utm.domain.external_utm.NullExternalUtm;
import utm.domain.external_utm.Unifly;

public class OperationManager {
	
	private static OperationManager instance;
	private List<Area> noFlyZones; // should probably be a Set
	private Map<OperationTemplate, PathCollection> paths;
	private ExternalUtm externalUtm;
	
	private OperationManager() {
		noFlyZones = new ArrayList<>();
		paths = new HashMap<>();
		try {
			externalUtm = new Unifly();
		}
		catch (IllegalStateException e) {
			System.err.println(e.getMessage() + ". Disabling Unifly communication.");
			externalUtm = new NullExternalUtm();
		}
	}
	
	public static OperationManager getInstance() {
		if (instance == null) {
  			instance = new OperationManager();
  		}
  		return instance;
	}
	
	private PathCollection execute(OperationTemplate operation) {
		TreeSet<PathCollection> candidates = new TreeSet<>();
		for (int i = 0; i < 5; i++) {
			ActionExecutorManager actionExecutorManager = new ActionExecutorManager();
			PathCollection pathCollection = actionExecutorManager.execute(operation, noFlyZones);
			candidates.add(pathCollection);
		}
		
		PathCollection pathCollection = candidates.first();
		paths.put(operation, pathCollection);
		return pathCollection;
	}
	
	public void onCreateOperation(OperationTemplate operation) throws IOException {
		PathCollection pathCollection = execute(operation);
		
		externalUtm.createUasOperations(pathCollection);
	}
	
	public void onAddNoFlyZone(Area newNoFlyZone) throws IOException {
		noFlyZones.add(newNoFlyZone);
		externalUtm.createNoFlyZone(newNoFlyZone);
		
		for (Map.Entry<OperationTemplate, PathCollection> entry : paths.entrySet()) {
			PathCollection pathCollection = execute(entry.getKey());
			// cancel by POST /api/uasoperations/<operationUuid>/cancellation
			externalUtm.createUasOperations(pathCollection);
		}
	}
	
	public List<Area> getNoFlyZones() {
		return noFlyZones;
	}
	
	public Set<OperationTemplate> getOperations() {
		return paths.keySet();
	}
	
}
