package utm.idsl.metamodel;

import utm.idsl.Visitor;

public interface PlanComponent {
	
	PlanComponent accept(Visitor visitor);
	
}
