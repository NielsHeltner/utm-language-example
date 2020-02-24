package utm.webservice.controllers;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.HttpMethod;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiRequestBody;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import utm.webservice.objects.ErrorResponse;
import utm.domain.missions.MissionMark;

public class MissionMarkController {
	
	@OpenApi(
		path = "/api/missions/mark", 
		method = HttpMethod.GET, 
		summary = "Summary", 
		operationId = "getMissionMark", 
		description = "Description", 
		tags = {"MissionMark"}, 
		responses = {
			@OpenApiResponse(status = "200", content = {@OpenApiContent(from = String.class)})
		}
	)
	public static void getMissionMark(Context ctx) {
		ctx.status(200);
	}
	
	@OpenApi(
		path = "/api/missions/mark", 
		method = HttpMethod.POST, 
		summary = "Summary", 
		operationId = "postMissionMark", 
		description = "Description", 
		tags = {"MissionMark"}, 
		requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = MissionMark.class)}), 
		responses = {
			@OpenApiResponse(status = "201"), 
			@OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
		}
	)
	public static void postMissionMark(Context ctx) {
		// Populate MissionMark object:
		//		MissionMark missionInfo = ctx.bodyAsClass(MissionMark.class)
		// Call utm.domain.planners.MissionMarkPlanner.getPlan(missionInfo);
		ctx.status(200);
	}
	
}

