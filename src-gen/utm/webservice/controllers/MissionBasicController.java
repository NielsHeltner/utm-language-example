package utm.webservice.controllers;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.HttpMethod;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiRequestBody;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import utm.domain.MissionManager;
import utm.domain.missions.MissionBasic;
import utm.domain.missions.templates.MissionBasicTemplate;
import utm.domain.UtmDynamic;
import utm.webservice.responses.ErrorResponse;

public class MissionBasicController {
	
	@OpenApi(
		path = "/api/missions/basic", 
		method = HttpMethod.GET, 
		summary = "Summary", 
		operationId = "getMissionBasic", 
		description = "Description", 
		tags = {"MissionBasic"}, 
		responses = {
			@OpenApiResponse(status = "200", content = {@OpenApiContent(from = String.class)})
		}
	)
	public static void getMissionBasic(Context ctx) {
		ctx.status(200);
	}
	
	@OpenApi(
		path = "/api/missions/basic", 
		method = HttpMethod.POST, 
		summary = "Summary", 
		operationId = "createMissionBasic", 
		description = "Description", 
		tags = {"MissionBasic"}, 
		requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = MissionBasic.class)}), 
		responses = {
			@OpenApiResponse(status = "201"), 
			@OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
		}
	)
	public static void createMissionBasic(Context ctx) {
		MissionBasic missionInfo = ctx.bodyValidator(MissionBasic.class).get();
		MissionManager.getInstance().onCreateMission(new MissionBasicTemplate(UtmDynamic.getInstance(), missionInfo));
		ctx.status(201);
	}
	
}
