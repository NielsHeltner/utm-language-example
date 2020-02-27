package utm.domain.idsl.model;

import java.util.List;

import utm.domain.idsl.interpreter.Visitor;

public class LeafAreaPath implements PathLeaf {
	
	public List<LeafNavigationPointPath> boundingBox;
	
	public LeafAreaPath(List<LeafNavigationPointPath> boundingBox) {
		this.boundingBox = boundingBox;
	}

	@Override
	public PathComponent accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
