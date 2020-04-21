package utm.dsl.metamodel;

import java.util.List;

public class ActionCollection {
	
	private List<AbstractAction> actions;
	
	public ActionCollection(List<AbstractAction> actions) {
		this.actions = actions;
	}
	
	public List<AbstractAction> getActions() {
		return actions;
	}
	
}
