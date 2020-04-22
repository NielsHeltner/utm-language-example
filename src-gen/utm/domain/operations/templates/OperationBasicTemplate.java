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
		drone(operation.drones).straight(operation.start).
		drone(operation.a).straight(operation.start).
		parallelForLoop(operation.c, operation.drones, (body0, b, d) -> {
			body0.parallelForLoop(operation.c, operation.drones, (body1, aa, z) -> {
				body1.drone(d).straight(b).
				drone(z).straight(aa);
			});
		});
	}
	
}
