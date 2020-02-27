package utm.domain.idsl.interpreter;

import java.util.ArrayList;
import java.util.List;

import utm.domain.idsl.model.CompositeCoverPath;
import utm.domain.idsl.model.CompositeStraightPath;
import utm.domain.idsl.model.LeafAreaPath;
import utm.domain.idsl.model.LeafNavigationPointPath;
import utm.domain.idsl.model.PathComponent;
import utm.domain.planners.Cover;
import utm.domain.datatypes.NavigationPoint;

public class PathPlanner implements Visitor {

	@Override
	public PathComponent visit(CompositeStraightPath straight) {
		PathComponent left = straight.left.accept(this);
		PathComponent right = straight.right.accept(this);
		
		return new Straight().plan(left, right);
	}

	@Override
	public PathComponent visit(CompositeCoverPath cover) {
		PathComponent target = cover.target.accept(this);
		
		return new Cover().plan(target);
	}

	@Override
	public PathComponent visit(LeafNavigationPointPath navigationPoint) {
		return navigationPoint;
	}

	@Override
	public PathComponent visit(LeafAreaPath area) {
		return area;
	}

	@Override
	public PathComponent visit(CompositePath path) {
		return path;
	}

}
