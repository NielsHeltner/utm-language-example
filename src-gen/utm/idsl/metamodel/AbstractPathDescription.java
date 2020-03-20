package utm.idsl.metamodel;

import java.util.List;
import utm.idsl.PathDescriptionVisitor;

public abstract class AbstractPathDescription {
	
	protected List<ILocation> locations;
	
	public AbstractPathDescription(List<ILocation> locations) {
		this.locations = locations;
	}
	
	public abstract void accept(PathDescriptionVisitor pathDescriptionVisitor);
	
	public List<ILocation> getLocations() {
		return this.locations;
	}
	
}
