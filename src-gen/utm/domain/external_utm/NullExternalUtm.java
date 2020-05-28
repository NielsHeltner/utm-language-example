package utm.domain.external_utm;

import java.io.IOException;

import utm.domain.action_executors.Path;
import utm.domain.action_executors.PathCollection;
import utm.domain.datatypes.Area;

public class NullExternalUtm implements ExternalUtm {
	
	@Override
	public void createUasOperations(PathCollection pathCollection) throws IOException {
		for (Path path : pathCollection.getPaths()) {
			createUasOperation(path);
		}
	}
	
	@Override
	public void createUasOperation(Path path) throws IOException {
		System.out.println("Mock creating UAS operation to external UTM from path: " + path);
	}
	
	@Override
	public void createNoFlyZone(Area noFlyZone) throws IOException {
		System.out.println("Mock creating no-fly zone to external UTM from area: " + noFlyZone);
	}
	
}
