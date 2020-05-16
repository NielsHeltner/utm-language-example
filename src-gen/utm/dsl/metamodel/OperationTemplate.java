package utm.dsl.metamodel;

public class OperationTemplate {
	
	private ActionCollection implementation;
	
	public OperationTemplate(ActionCollection implementation) {
		this.implementation = implementation;
	}
	
	public ActionCollection getImplementation() {
		return this.implementation;
	}
	
}
