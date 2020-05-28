package utm.webservice.controllers;

import java.io.IOException;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.HttpMethod;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiRequestBody;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import utm.domain.OperationManager;
import utm.domain.datatypes.Area;
import utm.webservice.responses.ErrorResponse;

public class NoFlyZonesController {
	
	@OpenApi(
		path = "/api/noflyzones", 
		method = HttpMethod.GET, 
		summary = "Summary", 
		operationId = "getNoFlyZones", 
		description = "Description", 
		tags = {"NoFlyZones"}, 
		responses = {
			@OpenApiResponse(status = "200", content = {@OpenApiContent(from = Area[].class)})
		}
	)
	public static void getNoFlyZones(Context ctx) {
		ctx.json(OperationManager.getInstance().getNoFlyZones());
	}
	
	@OpenApi(
		path = "/api/noflyzones", 
		method = HttpMethod.POST, 
		summary = "Summary", 
		operationId = "addNoFlyZone", 
		description = "Description", 
		tags = {"NoFlyZones"}, 
		requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Area.class)}), 
		responses = {
			@OpenApiResponse(status = "201"), 
			@OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
		}
	)
	public static void addNoFlyZone(Context ctx) throws IOException {
		Area noFlyZone = ctx.bodyAsClass(Area.class);
		OperationManager.getInstance().onAddNoFlyZone(noFlyZone);
		ctx.status(201);
	}
	
}
