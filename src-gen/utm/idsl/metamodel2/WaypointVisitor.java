package utm.idsl.metamodel2;

public interface WaypointVisitor {
	
	void visit(NavigationPointPlan navigationPointPlan);
	
	void visit(AreaPlan areaPlan);
	
	void visit(EachPlan eachPlan);
	
}
