package utm.dsl.metamodel;

public class ForLoopIteration {
	
	private UnresolvedDrone unresolvedDrone;
	private ActionCollection actionCollection;
	
	public ForLoopIteration(UnresolvedDrone unresolvedDrone, ActionCollection actionCollection) {
		this.unresolvedDrone = unresolvedDrone;
		this.actionCollection = actionCollection;
	}
	
	public ForLoopIteration(ActionCollection actionCollection) {
		this(null, actionCollection);
	}
	
	public UnresolvedDrone getUnresolvedDrone() {
		return unresolvedDrone;
	}
	
	public ActionCollection getActionCollection() {
		return actionCollection;
	}
	
}
