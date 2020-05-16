package utm.dsl.metamodel;

public class ForLoopIteration {
	
	private UnresolvedDrone unresolvedDrone;
	private ActionCollection body;
	
	public ForLoopIteration(UnresolvedDrone unresolvedDrone, ActionCollection body) {
		this.unresolvedDrone = unresolvedDrone;
		this.body = body;
	}
	
	public ForLoopIteration(ActionCollection body) {
		this(null, body);
	}
	
	public UnresolvedDrone getUnresolvedDrone() {
		return unresolvedDrone;
	}
	
	public ActionCollection getBody() {
		return body;
	}
	
}
