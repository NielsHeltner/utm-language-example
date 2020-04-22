package utm.domain.operations.templates;

import utm.dsl.ActionBuilder;
import utm.domain.operations.OperationSecond;
import utm.domain.UtmDynamic;

public class OperationSecondTemplate extends ActionBuilder {
	
	private UtmDynamic utm;
	private OperationSecond operation;
	
	public OperationSecondTemplate(UtmDynamic utm, OperationSecond operation) {
		this.utm = utm;
		this.operation = operation;
	}
	
	@Override
	protected void buildActions() {
		drone(operation.drones).straight(operation.start);
	}
	
}
