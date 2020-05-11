package utm.webservice.controllers;

import java.io.IOException;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.HttpMethod;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiRequestBody;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import utm.domain.OperationManager;
import utm.domain.operations.OperationBasic;
import utm.domain.operations.templates.OperationBasicTemplate;
import utm.domain.UtmDynamic;
import utm.webservice.responses.ErrorResponse;

public class OperationBasicController {
	
	@OpenApi(
		path = "/api/operations/basic", 
		method = HttpMethod.GET, 
		summary = "Summary", 
		operationId = "getOperationBasic", 
		description = "Description", 
		tags = {"OperationBasic"}, 
		responses = {
			@OpenApiResponse(status = "200", content = {@OpenApiContent(from = String.class)})
		}
	)
	public static void getOperationBasic(Context ctx) {
		ctx.status(200);
	}
	
	@OpenApi(
		path = "/api/operations/basic", 
		method = HttpMethod.POST, 
		summary = "Summary", 
		operationId = "createOperationBasic", 
		description = "Description", 
		tags = {"OperationBasic"}, 
		requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = OperationBasic.class)}), 
		responses = {
			@OpenApiResponse(status = "201"), 
			@OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
		}
	)
	public static void createOperationBasic(Context ctx) throws IOException {
		OperationBasic operationInfo = ctx.bodyValidator(OperationBasic.class).get();
		OperationManager.getInstance().onCreateOperation(new OperationBasicTemplate(UtmDynamic.getInstance(), operationInfo));
		ctx.status(201);
	}
	
}
