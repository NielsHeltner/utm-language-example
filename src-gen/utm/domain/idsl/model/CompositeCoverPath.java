package utm.domain.idsl.model;

import utm.domain.idsl.interpreter.Visitor;

public class CompositeCoverPath implements PathComponent {
	
	public PathLeaf target;
	
	public CompositeCoverPath(PathLeaf target) {
		this.target = target;
	}

	@Override
	public PathComponent accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
