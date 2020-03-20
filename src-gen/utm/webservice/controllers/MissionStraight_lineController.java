package utm.webservice.controllers;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.HttpMethod;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiRequestBody;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import utm.webservice.objects.ErrorResponse;
import utm.domain.missions.MissionStraight_line;
import utm.domain.missions.path_descriptions.MissionStraight_linePathDescription;
import utm.domain.MissionManager;
import utm.domain.PathPlannerManager;

public class MissionStraight_lineController {
	
	@OpenApi(
		path = "/api/missions/straight_line", 
		method = HttpMethod.GET, 
		summary = "Summary", 
		operationId = "getMissionStraight_line", 
		description = "Description", 
		tags = {"MissionStraight_line"}, 
		responses = {
			@OpenApiResponse(status = "200", content = {@OpenApiContent(from = String.class)})
		}
	)
	public static void getMissionStraight_line(Context ctx) {
		ctx.status(200);
	}
	
	@OpenApi(
		path = "/api/missions/straight_line", 
		method = HttpMethod.POST, 
		summary = "Summary", 
		operationId = "createMissionStraight_line", 
		description = "Description", 
		tags = {"MissionStraight_line"}, 
		requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = MissionStraight_line.class)}), 
		responses = {
			@OpenApiResponse(status = "201"), 
			@OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
		}
	)
	public static void createMissionStraight_line(Context ctx) {
		MissionStraight_line missionInfo = ctx.bodyAsClass(MissionStraight_line.class);
		MissionManager.getInstance().onCreateMission(new MissionStraight_linePathDescription(missionInfo), new PathPlannerManager());
		ctx.status(201);
	}
	
}

