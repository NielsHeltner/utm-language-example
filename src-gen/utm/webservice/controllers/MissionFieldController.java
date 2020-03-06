package utm.webservice.controllers;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.HttpMethod;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiRequestBody;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import utm.webservice.objects.ErrorResponse;
import utm.domain.missions.MissionField;

public class MissionFieldController {
	
	@OpenApi(
		path = "/api/missions/field", 
		method = HttpMethod.GET, 
		summary = "Summary", 
		operationId = "getMissionField", 
		description = "Description", 
		tags = {"MissionField"}, 
		responses = {
			@OpenApiResponse(status = "200", content = {@OpenApiContent(from = String.class)})
		}
	)
	public static void getMissionField(Context ctx) {
		ctx.status(200);
	}
	
	@OpenApi(
		path = "/api/missions/field", 
		method = HttpMethod.POST, 
		summary = "Summary", 
		operationId = "postMissionField", 
		description = "Description", 
		tags = {"MissionField"}, 
		requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = MissionField.class)}), 
		responses = {
			@OpenApiResponse(status = "201"), 
			@OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
		}
	)
	public static void postMissionField(Context ctx) {
		// Populate MissionField object:
		//		MissionField missionInfo = ctx.bodyAsClass(MissionField.class)
		// Call utm.domain.missions.path_descriptions.MissionFieldPathDescription.getPlan(missionInfo);
		ctx.status(200);
	}
	
}

