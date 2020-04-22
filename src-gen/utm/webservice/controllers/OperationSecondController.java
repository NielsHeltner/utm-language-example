package utm.webservice.controllers;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.HttpMethod;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiRequestBody;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import utm.domain.OperationManager;
import utm.domain.operations.OperationSecond;
import utm.domain.operations.templates.OperationSecondTemplate;
import utm.domain.UtmDynamic;
import utm.webservice.responses.ErrorResponse;

public class OperationSecondController {
	
	@OpenApi(
		path = "/api/operations/second", 
		method = HttpMethod.GET, 
		summary = "Summary", 
		operationId = "getOperationSecond", 
		description = "Description", 
		tags = {"OperationSecond"}, 
		responses = {
			@OpenApiResponse(status = "200", content = {@OpenApiContent(from = String.class)})
		}
	)
	public static void getOperationSecond(Context ctx) {
		ctx.status(200);
	}
	
	@OpenApi(
		path = "/api/operations/second", 
		method = HttpMethod.POST, 
		summary = "Summary", 
		operationId = "createOperationSecond", 
		description = "Description", 
		tags = {"OperationSecond"}, 
		requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = OperationSecond.class)}), 
		responses = {
			@OpenApiResponse(status = "201"), 
			@OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
		}
	)
	public static void createOperationSecond(Context ctx) {
		OperationSecond operationInfo = ctx.bodyValidator(OperationSecond.class).get();
		OperationManager.getInstance().onCreateOperation(new OperationSecondTemplate(UtmDynamic.getInstance(), operationInfo));
		ctx.status(201);
	}
	
}
