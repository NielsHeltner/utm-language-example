package webservice;

import io.javalin.Javalin;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.ReDocOptions;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;
import webservice.objects.ErrorResponse;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Server {

	public static void main(String[] args) {
        Javalin.create(config -> {
        	config.registerPlugin(getConfiguredOpenApiPlugin());
        	config.defaultContentType = "application/json";
        	config.enableCorsForAllOrigins();
        	config.enableDevLogging();
        }).routes(() -> {
			path("/", () -> {
				get(ctx -> ctx.json("hi"));
				path("/drone", () -> {
					get(ctx -> ctx.json("get drone"));
					post(ctx -> ctx.json("post done"));
				});
			});
		}).start(7000);
	}
	
	private static OpenApiPlugin getConfiguredOpenApiPlugin() {
        Info info = new Info().version("1.0").description("User API");
        OpenApiOptions options = new OpenApiOptions(info)
                .activateAnnotationScanningFor("io.javalin.example.java")
                .path("/swagger-docs") // endpoint for OpenAPI json
                .swagger(new SwaggerOptions("/swagger-ui")) // endpoint for swagger-ui
                .reDoc(new ReDocOptions("/redoc")) // endpoint for redoc
                .defaultDocumentation(doc -> {
                    doc.json("500", ErrorResponse.class);
                	doc.json("503", ErrorResponse.class);
                });
        return new OpenApiPlugin(options);
    }

}

