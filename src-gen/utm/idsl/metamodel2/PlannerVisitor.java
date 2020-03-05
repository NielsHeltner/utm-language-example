package utm.idsl.metamodel2;

public interface PlannerVisitor {
	
	void visit(StraightPlan straightPlan);
	
	void visit(CoverPlan coverPlan);
	
}
