package utm.domain.operations.templates;

import utm.dsl.ActionBuilder;
import utm.domain.operations.OperationBasic;
import utm.domain.UtmDynamic;

public class OperationBasicTemplate extends ActionBuilder {
	
	private UtmDynamic utm;
	private OperationBasic operation;
	
	public OperationBasicTemplate(UtmDynamic utm, OperationBasic operation) {
		this.utm = utm;
		this.operation = operation;
	}
	
	@Override
	protected void buildActions() {
		drone(operation.uas).straight(operation.points);
	}
	
}
