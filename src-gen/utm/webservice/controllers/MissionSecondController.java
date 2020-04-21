package utm.webservice.controllers;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.HttpMethod;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiRequestBody;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import utm.domain.MissionManager;
import utm.domain.missions.MissionSecond;
import utm.domain.missions.templates.MissionSecondTemplate;
import utm.domain.UtmDynamic;
import utm.webservice.responses.ErrorResponse;

public class MissionSecondController {
	
	@OpenApi(
		path = "/api/missions/second", 
		method = HttpMethod.GET, 
		summary = "Summary", 
		operationId = "getMissionSecond", 
		description = "Description", 
		tags = {"MissionSecond"}, 
		responses = {
			@OpenApiResponse(status = "200", content = {@OpenApiContent(from = String.class)})
		}
	)
	public static void getMissionSecond(Context ctx) {
		ctx.status(200);
	}
	
	@OpenApi(
		path = "/api/missions/second", 
		method = HttpMethod.POST, 
		summary = "Summary", 
		operationId = "createMissionSecond", 
		description = "Description", 
		tags = {"MissionSecond"}, 
		requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = MissionSecond.class)}), 
		responses = {
			@OpenApiResponse(status = "201"), 
			@OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
		}
	)
	public static void createMissionSecond(Context ctx) {
		MissionSecond missionInfo = ctx.bodyValidator(MissionSecond.class).get();
		MissionManager.getInstance().onCreateMission(new MissionSecondTemplate(UtmDynamic.getInstance(), missionInfo));
		ctx.status(201);
	}
	
}
