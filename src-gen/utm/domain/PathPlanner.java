package utm.domain;

import java.util.List;
import java.util.ArrayList;

import utm.domain.datatypes.Area;
import utm.domain.datatypes.NavigationPoint;
import utm.domain.planners.CoverPathPlanner;
import utm.domain.planners.IPathPlanner;
import utm.domain.planners.StraightPathPlanner;

import utm.idsl.PathDescriptionVisitor;
import utm.idsl.metamodel.AbstractPathDescription;
import utm.idsl.metamodel.Cover;
import utm.idsl.metamodel.ILocation;
import utm.idsl.metamodel.MetaModel;
import utm.idsl.metamodel.Straight;

public class PathPlanner implements PathDescriptionVisitor {
	
	private IPathPlanner straightPlanner;
	private IPathPlanner coverPlanner;
	
	private List<NavigationPoint> path;
	private NavigationPoint currentPos;
	
	private List<Area> noFlyZones;
	
	public PathPlanner() {
		this.straightPlanner = new StraightPathPlanner();
		this.coverPlanner = new CoverPathPlanner();
		
		this.path = new ArrayList<>();
	}
	
	public List<NavigationPoint> plan(MetaModel metaModel, List<Area> noFlyZones) {
		this.noFlyZones = noFlyZones;
		for (AbstractPathDescription pathDescription : metaModel.getPathDescriptions()) {
			pathDescription.accept(this);
		}
		
		return path;
	}
	
	@Override
	public void visit(Straight straight) {
		List<ILocation> locations = straight.getLocations();
		path.addAll(straightPlanner.plan(currentPos, locations, noFlyZones));
		currentPos = path.get(path.size() - 1);
	}
	
	@Override
	public void visit(Cover cover) {
		List<ILocation> locations = cover.getLocations();
		path.addAll(coverPlanner.plan(currentPos, locations, noFlyZones));
		currentPos = path.get(path.size() - 1);
	}
	
}
