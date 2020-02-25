package utm.webservice.controllers;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.HttpMethod;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiRequestBody;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import utm.webservice.objects.ErrorResponse;
import utm.domain.missions.MissionFollow_vehicle;

public class MissionFollow_vehicleController {
	
	@OpenApi(
		path = "/api/missions/follow_vehicle", 
		method = HttpMethod.GET, 
		summary = "Summary", 
		operationId = "getMissionFollow_vehicle", 
		description = "Description", 
		tags = {"MissionFollow_vehicle"}, 
		responses = {
			@OpenApiResponse(status = "200", content = {@OpenApiContent(from = String.class)})
		}
	)
	public static void getMissionFollow_vehicle(Context ctx) {
		ctx.status(200);
	}
	
	@OpenApi(
		path = "/api/missions/follow_vehicle", 
		method = HttpMethod.POST, 
		summary = "Summary", 
		operationId = "postMissionFollow_vehicle", 
		description = "Description", 
		tags = {"MissionFollow_vehicle"}, 
		requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = MissionFollow_vehicle.class)}), 
		responses = {
			@OpenApiResponse(status = "201"), 
			@OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
		}
	)
	public static void postMissionFollow_vehicle(Context ctx) {
		// Populate MissionFollow_vehicle object:
		//		MissionFollow_vehicle missionInfo = ctx.bodyAsClass(MissionFollow_vehicle.class)
		// Call utm.domain.missions.planners.MissionFollow_vehiclePlanner.getPlan(missionInfo);
		ctx.status(200);
	}
	
}

