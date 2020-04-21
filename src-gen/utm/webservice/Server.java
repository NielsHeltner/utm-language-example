package utm.webservice;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.module.SimpleModule;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJackson;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.annotations.HttpMethod;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;
import utm.domain.datatypes.Location;
import utm.webservice.objectmapper.LocationObjectMapper;

import utm.webservice.controllers.MissionBasicController;
import utm.webservice.controllers.MissionSecondController;
import utm.webservice.controllers.NoFlyZonesController;
import utm.webservice.responses.ErrorResponse;

public class Server {
	
	public static void main(String[] args) {
		Server.launch();
	}
	
	public static void launch() {
        Javalin.create(config -> {
        	config.registerPlugin(getConfiguredOpenApiPlugin());
        	config.defaultContentType = "application/json";
        	config.enableCorsForAllOrigins();
        	config.enableDevLogging();

			SimpleModule module = new SimpleModule();
			module.addDeserializer(Location.class, new LocationObjectMapper());
			JavalinJackson.getObjectMapper().registerModule(module);
        }).routes(() -> {
			path("/", () -> {
				get(Server::getRoot);
				path("api", () -> {
					get(ctx -> ctx.redirect("swagger-ui"));
					path("noflyzones", () -> {
						get(NoFlyZonesController::getNoFlyZones);
						post(NoFlyZonesController::addNoFlyZone);
					});
					path("missions", () -> {
						path("basic", () -> {
							get(MissionBasicController::getMissionBasic);
							post(MissionBasicController::createMissionBasic);
						});
						path("second", () -> {
							get(MissionSecondController::getMissionSecond);
							post(MissionSecondController::createMissionSecond);
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
		ctx.json(new JSONObject()
				.put("links", new JSONArray()
					.put("/swagger-docs")
					.put("/swagger-ui")
					.put("/api")
					.put("/api/noflyzones")
					.put("/api/missions")
					.put("/api/missions/basic")
					.put("/api/missions/second")
				).toMap());
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
