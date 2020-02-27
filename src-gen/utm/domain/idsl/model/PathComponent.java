package utm.domain.idsl.model;

import utm.domain.idsl.interpreter.Visitor;

public interface PathComponent {
	
	PathComponent accept(Visitor visitor);

}
