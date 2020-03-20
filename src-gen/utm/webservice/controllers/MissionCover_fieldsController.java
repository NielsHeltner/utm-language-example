package utm.webservice.controllers;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.HttpMethod;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiRequestBody;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import utm.webservice.objects.ErrorResponse;
import utm.domain.missions.MissionCover_fields;
import utm.domain.missions.path_descriptions.MissionCover_fieldsPathDescription;
import utm.domain.MissionManager;
import utm.domain.PathPlannerManager;

public class MissionCover_fieldsController {
	
	@OpenApi(
		path = "/api/missions/cover_fields", 
		method = HttpMethod.GET, 
		summary = "Summary", 
		operationId = "getMissionCover_fields", 
		description = "Description", 
		tags = {"MissionCover_fields"}, 
		responses = {
			@OpenApiResponse(status = "200", content = {@OpenApiContent(from = String.class)})
		}
	)
	public static void getMissionCover_fields(Context ctx) {
		ctx.status(200);
	}
	
	@OpenApi(
		path = "/api/missions/cover_fields", 
		method = HttpMethod.POST, 
		summary = "Summary", 
		operationId = "createMissionCover_fields", 
		description = "Description", 
		tags = {"MissionCover_fields"}, 
		requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = MissionCover_fields.class)}), 
		responses = {
			@OpenApiResponse(status = "201"), 
			@OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
		}
	)
	public static void createMissionCover_fields(Context ctx) {
		MissionCover_fields missionInfo = ctx.bodyAsClass(MissionCover_fields.class);
		MissionManager.getInstance().onCreateMission(new MissionCover_fieldsPathDescription(missionInfo), new PathPlannerManager());
		ctx.status(201);
	}
	
}

