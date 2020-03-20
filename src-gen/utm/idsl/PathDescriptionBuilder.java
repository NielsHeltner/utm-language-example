package utm.idsl;

import java.util.Arrays;

import utm.idsl.metamodel.MetaModel;
import utm.idsl.metamodel.AbstractPathDescription;
import utm.idsl.metamodel.ILocation;
import utm.idsl.metamodel.ForLoop;
import utm.idsl.metamodel.Straight;
import utm.idsl.metamodel.Cover;
import utm.idsl.metamodel.NavigationPointLocation;
import utm.domain.datatypes.NavigationPoint;
import utm.idsl.metamodel.AreaLocation;
import utm.domain.datatypes.Area;

public abstract class PathDescriptionBuilder {
	
	private MetaModel metaModel;
	
	public PathDescriptionBuilder() {
		this.metaModel = new MetaModel();
	}
	
	protected abstract boolean verifyConstraints();
	
	protected abstract void buildPathDescription();
	
	public MetaModel getPathDescriptions() {
		if (verifyConstraints()) {
			buildPathDescription();
			return metaModel;
		}
		else {
			return null;
		}
	}
	
	public PathDescriptionBuilder straight(ILocation... locations) {
		AbstractPathDescription pathDescription = new Straight(Arrays.asList(locations));
		metaModel.add(pathDescription);
		return this;
	}
	
	public PathDescriptionBuilder cover(ILocation... locations) {
		AbstractPathDescription pathDescription = new Cover(Arrays.asList(locations));
		metaModel.add(pathDescription);
		return this;
	}
	
	public ILocation navigationPoint(NavigationPoint navigationPoint) {
		return new NavigationPointLocation(navigationPoint);
	}
	
	public ILocation area(Area area) {
		return new AreaLocation(area);
	}
	
	
	public ILocation forLoop(ILocation... locations) {
		return new ForLoop(Arrays.asList(locations), null, null);
	}
}
