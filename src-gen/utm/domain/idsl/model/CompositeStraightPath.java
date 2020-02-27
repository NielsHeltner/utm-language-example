package utm.domain.idsl.model;

import utm.domain.idsl.interpreter.Visitor;
import utm.domain.idsl.interpreter.BinaryVisitor;

public class CompositeStraightPath implements PathComponent {
	
	public PathComponent left;
	public PathComponent right;
	
	public CompositeStraightPath(PathComponent left, PathComponent right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public PathComponent accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
