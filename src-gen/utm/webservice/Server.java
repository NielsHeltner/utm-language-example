package utm.webservice;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.annotations.HttpMethod;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;
import utm.webservice.objects.ErrorResponse;
import utm.webservice.controllers.MissionMarkController;
import utm.webservice.controllers.MissionFollow_vehicleController;

// imports for test
import utm.domain.datatypes.NavigationPoint;
import utm.domain.datatypes.Area;
import utm.domain.missions.MissionFollow_vehicle;
import utm.domain.missions.planners.MissionFollow_vehiclePlanner;
import java.util.Arrays;

public class Server {

	public static void main(String[] args) {
		NavigationPoint start = new NavigationPoint("0", "0");
		NavigationPoint point1 = new NavigationPoint("1", "1");
		NavigationPoint point2 = new NavigationPoint("9", "9");
		NavigationPoint end = new NavigationPoint("10", "10");
		
		Area zone = new Area(Arrays.asList(
			new NavigationPoint("3", "3"), 
			new NavigationPoint("4", "4"), 
			new NavigationPoint("5", "5"), 
			new NavigationPoint("6", "6")
		));
		
		MissionFollow_vehicle mission = new MissionFollow_vehicle(start, point1, zone, point2, end);
		MissionFollow_vehiclePlanner planner = new MissionFollow_vehiclePlanner(mission);

		for (NavigationPoint p : planner.createPlan()) {
			System.out.println(p.getLat() + " " + p.getLon());
		}
		
		
		
        Javalin.create(config -> {
        	config.registerPlugin(getConfiguredOpenApiPlugin());
        	config.defaultContentType = "application/json";
        	config.enableCorsForAllOrigins();
        	config.enableDevLogging();
        }).routes(() -> {
			path("/", () -> {
				get(Server::getRoot);
				path("api", () -> {
					get(ctx -> ctx.redirect("swagger-ui"));
					path("missions", () -> {
						path("mark", () -> {
							get(MissionMarkController::getMissionMark);
							post(MissionMarkController::postMissionMark);
						});
						path("follow_vehicle", () -> {
							get(MissionFollow_vehicleController::getMissionFollow_vehicle);
							post(MissionFollow_vehicleController::postMissionFollow_vehicle);
						});
					});
				});
			});
		}).start(7000);
	}
	
	@OpenApi(
		path = "/", 
		method = HttpMethod.GET, 
		summary = "Summary", 
		operationId = "getRoot", 
		description = "Description", 
		tags = {"Root"}, 
		responses = {
			@OpenApiResponse(status = "200", content = {@OpenApiContent(from = String.class)})
		}
	)
	public static void getRoot(Context ctx) {
		ctx.json("Hello from GET root");
	}
	
	private static OpenApiPlugin getConfiguredOpenApiPlugin() {
        Info info = new Info().version("1.0").description("User API");
        OpenApiOptions options = new OpenApiOptions(info)
                .activateAnnotationScanningFor("utm.webservice")
                .path("/swagger-docs") // endpoint for OpenAPI json
                .swagger(new SwaggerOptions("/swagger-ui")) // endpoint for swagger-ui
                .defaultDocumentation(doc -> {
                    doc.json("500", ErrorResponse.class);
                	doc.json("503", ErrorResponse.class);
                });
        return new OpenApiPlugin(options);
    }

}

