package utm.domain.idsl.model;

import utm.domain.idsl.interpreter.Visitor;

public class LeafNavigationPointPath implements PathLeaf {
	
	public double lat, lon;
	
	public LeafNavigationPointPath(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	@Override
	public PathComponent accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
