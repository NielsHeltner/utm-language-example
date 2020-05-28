package utm.domain.external_utm;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import utm.domain.action_executors.Path;
import utm.domain.action_executors.PathCollection;
import utm.domain.datatypes.Area;

public class Unifly implements ExternalUtm {
	
	private OkHttpClient client;
	private String accessToken;
	private final String baseUrl = "https://healthdrone.unifly.tech";
	private final String apiUrl = baseUrl + "/api";
	private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private final MediaType GEOJSON = MediaType.parse("application/vnd.geo+json; charset=utf-8");
	
	public Unifly() {
		client = new OkHttpClient.Builder()
			.authenticator(new UniflyAuthenticator())
			.build();
	}
	
	@Override
	public void createUasOperations(PathCollection pathCollection) throws IOException {
		for (Path path : pathCollection.getPaths()) {
			createUasOperation(path);
		}
	}
	
	@Override
	public void createUasOperation(Path path) throws IOException {
		JSONObject pilot = getMyOperatorUser();
		JSONObject operation = new JSONObject()
			.put("type", "Feature")
			.put("properties", new JSONObject()
				.put("name", "UTM DSL drone path")
				.put("minHeight", 0)
				.put("maxHeight", 100)
				.put("startTime", path.getStartTime())
				.put("endTime", path.getEndTime())
				//.put("uas", "988af98b-03e6-402d-b017-361cb24ebbf8")
				.put("uas", path.getDrone().uuid)
				.put("pilotUuid", pilot.get("user"))
				.put("lineOfSightType", "B_VLOS")
				.put("rulesetCode", "Commercial")
				.put("takeOffPosition", new JSONObject()
					.put("latitude", path.getFirst().lat)
					.put("longitude", path.getFirst().lon))
				.put("landPosition", new JSONObject()
					.put("latitude", path.getLast().lat)
					.put("longitude", path.getLast().lon))
			)
			.put("geometry", new JSONObject()
				.put("type", "LineString")
				.put("coordinates", new JSONArray(
						path.getNavigationPoints().stream()
						.map(navigationPoint -> Arrays.asList(navigationPoint.lon, navigationPoint.lat))
						.collect(Collectors.toList())))
			);
		Request postUasoperation = new Request.Builder()
			.url(apiUrl + "/uasoperations")
			.post(RequestBody.create(operation.toString(), GEOJSON))
			.build();
		
		System.out.println(request(postUasoperation, response -> response.body().string()));
	}
	
	@Override
	public void createNoFlyZone(Area noFlyZone) throws IOException {
		JSONObject uas = postUasForNoFlyZone();
		JSONObject pilot = getMyOperatorUser();
		JSONObject operation = new JSONObject()
			.put("type", "Feature")
			.put("properties", new JSONObject()
				.put("name", "UTM DSL no-fly zone")
				.put("minHeight", 0)
				.put("maxHeight", 100)
				.put("startTime", Instant.now())
				.put("endTime", Instant.now().plus(30, ChronoUnit.SECONDS))
				.put("uas", uas.get("uniqueIdentifier"))
				.put("pilotUuid", pilot.get("user"))
				.put("lineOfSightType", "B_VLOS")
				.put("rulesetCode", "Commercial")
				.put("takeOffPosition", new JSONObject()
					.put("latitude", noFlyZone.boundingBox.get(0).lat)
					.put("longitude", noFlyZone.boundingBox.get(0).lon))
				.put("landPosition", new JSONObject()
					.put("latitude", noFlyZone.boundingBox.get(noFlyZone.boundingBox.size() - 1).lat)
					.put("longitude", noFlyZone.boundingBox.get(noFlyZone.boundingBox.size() - 1).lon))
			)
			.put("geometry", new JSONObject()
				.put("type", "Polygon")
				.put("coordinates", new JSONArray(
						Arrays.asList(noFlyZone.boundingBox.stream()
						.map(point -> Arrays.asList(point.lon, point.lat))
						.collect(Collectors.toList()))))
			);
		Request postUasoperation = new Request.Builder()
			.url(apiUrl + "/uasoperations")
			.post(RequestBody.create(operation.toString(), GEOJSON))
			.build();
		
		System.out.println(request(postUasoperation, response -> response.body().string()));
	}
	
	private JSONObject getMyOperatorUser() throws IOException {
		Request getMe = new Request.Builder()
			.url(apiUrl + "/operator/users/me")
			.build();
		
		return request(getMe, response -> new JSONObject(response.body().string()));
	}
	
	private JSONObject postUasForNoFlyZone() throws IOException {
		JSONObject uas = new JSONObject()
			.put("nickname", "No-fly zone")
			.put("generalFeatures", new JSONObject()
				.put("brand", "UTM DSL")
				.put("model", "No-fly zone"));
		
		Request postUas = new Request.Builder()
			.url(apiUrl + "/uases")
			.post(RequestBody.create(uas.toString(), JSON))
			.build();
		
		return request(postUas, response -> new JSONObject(response.body().string()));
	}
	
	private <R> R request(Request request, ResponseHandler<R> callback) throws IOException {
		if (request.header("Authorization") == null && accessToken != null) { // add auth header if it's missing and we have an accesstoken
			request = request.newBuilder()
				.header("Authorization", getAccessToken())
				.build();
		}
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) {
			throw new IOException("Unexpected response: " + response 
					+ "\n with body: " + response.body().string());
		}
		R result = callback.apply(response);
		response.close();
		return result;
	}
	
	private String getAccessToken() {
		return "Bearer " + accessToken;
	}
	
	@FunctionalInterface
	private interface ResponseHandler<R> {
		
		R apply(Response response) throws IOException;
		
	}
	
	private class UniflyAuthenticator implements Authenticator {
		
		private JSONObject credentials;
		
		public UniflyAuthenticator() {
			try {
				credentials = new JSONObject(new JSONTokener(new FileReader("creds.json")));
			}
			catch (FileNotFoundException | JSONException e) {
				throw new IllegalStateException("Could not find or parse Unifly credentials \"creds.json\"");
			}
		}
		
		@Override
		public Request authenticate(Route route, Response response) throws IOException {
			if (response.request().header("Authorization") != null) {
				return null; // give up, we've already failed to authenticate
			}
			String basicCredentials = Credentials.basic(credentials.getString("clientid"), credentials.getString("clientsecret"));
			
			RequestBody body = new FormBody.Builder()
				.addEncoded("username", credentials.getString("username"))
				.addEncoded("password", credentials.getString("password"))
				.addEncoded("grant_type", "password")
				.build();
			
			Request authRequest = new Request.Builder()
				.url(baseUrl + "/oauth/token")
				.header("Authorization", basicCredentials)
				.header("Accept", "application/json")
				.post(body)
				.build();
			
			accessToken = request(authRequest, authResponse -> {
				JSONObject authResponseBody = new JSONObject(authResponse.body().string());
				return authResponseBody.getString("access_token");
			});
			return response.request().newBuilder()
					.header("Authorization", getAccessToken())
					.build();
		}
		
	}
	
}
