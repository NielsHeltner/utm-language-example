package utm.domain.external_utm;

import java.io.IOException;

import utm.domain.action_executors.Path;
import utm.domain.action_executors.PathCollection;
import utm.domain.datatypes.Area;

public interface ExternalUtm {
	
	void createUasOperations(PathCollection pathCollection) throws IOException;
	
	void createUasOperation(Path path) throws IOException;
	
	void createNoFlyZone(Area noFlyZone) throws IOException;
	
}
