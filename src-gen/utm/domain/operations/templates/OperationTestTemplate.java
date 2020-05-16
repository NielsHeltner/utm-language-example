package utm.domain.operations.templates;

import utm.dsl.ActionBuilder;
import utm.domain.operations.OperationTest;
import utm.domain.UtmDynamic;

public class OperationTestTemplate extends ActionBuilder {
	
	private UtmDynamic utm;
	private OperationTest operation;
	
	public OperationTestTemplate(UtmDynamic utm, OperationTest operation) {
		this.utm = utm;
		this.operation = operation;
	}
	
	@Override
	protected void buildActions() {
		drone(operation.drones).straight(array(operation.start)).
		drone(operation.drones).straight(operation.start).
		drone(operation.a).straight(operation.start).
		sequentialForLoop(array(operation.point, operation.point), operation.drones, (body, variable, drone_variable) -> {
			body.drone(drone_variable).straight(variable);
		}).
		prioritizedForLoop(operation.drones, (body, d) -> {
			body.drone(d).straight(operation.locations);
		}).
		parallelForLoop(operation.locations, operation.drones, (body0, i, x) -> {
			body0.parallelForLoop(operation.locations, operation.drones, (body1, j, y) -> {
				body1.drone(x).straight(i).
				drone(y).straight(j);
			});
		});
	}
	
}
