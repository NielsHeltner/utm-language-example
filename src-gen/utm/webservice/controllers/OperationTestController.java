package utm.webservice.controllers;

import java.io.IOException;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.HttpMethod;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiRequestBody;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import utm.domain.OperationManager;
import utm.domain.operations.OperationTest;
import utm.domain.operations.templates.OperationTestTemplate;
import utm.domain.UtmDynamic;
import utm.webservice.responses.ErrorResponse;

public class OperationTestController {
	
	@OpenApi(
		path = "/api/operations/test", 
		method = HttpMethod.GET, 
		summary = "Summary", 
		operationId = "getOperationTest", 
		description = "Description", 
		tags = {"OperationTest"}, 
		responses = {
			@OpenApiResponse(status = "200")
		}
	)
	public static void getOperationTest(Context ctx) {
		ctx.status(200);
	}
	
	@OpenApi(
		path = "/api/operations/test", 
		method = HttpMethod.POST, 
		summary = "Summary", 
		operationId = "createOperationTest", 
		description = "Description", 
		tags = {"OperationTest"}, 
		requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = OperationTest.class)}), 
		responses = {
			@OpenApiResponse(status = "201"), 
			@OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
		}
	)
	public static void createOperationTest(Context ctx) throws IOException {
		OperationTest operationInfo = ctx.bodyValidator(OperationTest.class).get();
		OperationManager.getInstance().onCreateOperation(new OperationTestTemplate(UtmDynamic.getInstance(), operationInfo).getOperationTemplate());
		ctx.status(201);
	}
	
}
