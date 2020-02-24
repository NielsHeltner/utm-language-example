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

public class Server {

	public static void main(String[] args) {
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

