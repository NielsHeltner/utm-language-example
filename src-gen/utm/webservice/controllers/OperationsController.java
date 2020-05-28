package utm.webservice.controllers;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.HttpMethod;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import utm.domain.OperationManager;
import utm.dsl.metamodel.OperationTemplate;

public class OperationsController {
	
	@OpenApi(
		path = "/api/operations", 
		method = HttpMethod.GET, 
		summary = "Summary", 
		operationId = "getOperations", 
		description = "Description", 
		tags = {"Operations"}, 
		responses = {
			@OpenApiResponse(status = "200", content = {@OpenApiContent(from = OperationTemplate[].class)})
		}
	)
	public static void getOperations(Context ctx) {
		ctx.json(OperationManager.getInstance().getOperations());
	}
	
}
