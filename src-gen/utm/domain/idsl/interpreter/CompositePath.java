package utm.domain.idsl.interpreter;

import java.util.List;

import utm.domain.idsl.model.PathComponent;

public class CompositePath implements PathComponent {
	
	public List<PathComponent> children;
	
	public CompositePath(List<PathComponent> children) {
		this.children = children;
	}

	@Override
	public PathComponent accept(Visitor visitor) {
		return this;
	}

}
