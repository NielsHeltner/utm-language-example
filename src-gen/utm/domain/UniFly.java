package utm.domain;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import utm.domain.datatypes.Area;
import utm.domain.datatypes.NavigationPoint;

public class UniFly {
	
	private OkHttpClient client;
	private String accessToken;
	private final String baseUrl = "https://healthdrone.unifly.tech";
	private final String apiUrl = baseUrl + "/api";
	private final MediaType GEOJSON = MediaType.parse("application/vnd.geo+json; charset=utf-8");
	
	public UniFly() {
		client = new OkHttpClient.Builder()
			.authenticator(new UniFlyAuthenticator())
			.build();
	}
	
	public void createUasOperation(List<NavigationPoint> path) {
		JSONObject pilot = getMyOperatorUser();
		JSONObject mission = new JSONObject()
				.put("type", "Feature")
				.put("properties", new JSONObject()
						.put("name", "UTM DSL path")
						.put("minHeight", 0)
						.put("maxHeight", 100)
						.put("startTime", Instant.now())
						.put("endTime", Instant.now().plus(30, ChronoUnit.SECONDS))
						.put("uas", "988af98b-03e6-402d-b017-361cb24ebbf8")
						.put("pilotUuid", pilot.get("user"))
						.put("lineOfSightType", "B_VLOS")
						.put("rulesetCode", "Commercial")
						.put("takeOffPosition", new JSONObject()
								.put("latitude", path.get(0).lat)
								.put("longitude", path.get(0).lon))
						.put("landPosition", new JSONObject()
								.put("latitude", path.get(path.size() - 1).lat)
								.put("longitude", path.get(path.size() - 1).lon))
				)
		.put("geometry", new JSONObject()
				.put("type", "LineString")
				.put("coordinates", new JSONArray(path.stream().map((navigationPoint) -> Arrays.asList(navigationPoint.lon, navigationPoint.lat)).collect(Collectors.toList())))
		);
		Request postUasoperation = new Request.Builder()
				.url("https://healthdrone.unifly.tech/api/uasoperations")
				.post(RequestBody.create(mission.toString(), GEOJSON))
				.build();
		
		System.out.println(request(postUasoperation, (response) -> response.body().string()));
	}
	
	public void createNoFlyZone(Area noFlyZone) {
		JSONObject pilot = getMyOperatorUser();
		JSONObject mission = new JSONObject()
				.put("type", "Feature")
				.put("properties", new JSONObject()
						.put("name", "UTM DSL no fly zone")
						.put("minHeight", 0)
						.put("maxHeight", 100)
						.put("startTime", Instant.now())
						.put("endTime", Instant.now().plus(30, ChronoUnit.SECONDS))
						.put("uas", "988af98b-03e6-402d-b017-361cb24ebbf8")
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
						.put("coordinates", new JSONArray(Arrays.asList(noFlyZone.boundingBox.stream().map((bb) -> Arrays.asList(bb.lon, bb.lat)).collect(Collectors.toList()))))
				);
		Request postUasoperation = new Request.Builder()
				.url(apiUrl + "/uasoperations")
				.post(RequestBody.create(mission.toString(), GEOJSON))
				.build();
		
		System.out.println(request(postUasoperation, (response) -> response.body().string()));
	}
	
	private JSONObject getMyOperatorUser() {
		Request getMe = new Request.Builder()
				.url(apiUrl + "/operator/users/me")
				.build();
		
		return request(getMe, (response) -> new JSONObject(response.body().string()));
	}
	
	private <R> R request(Request request, ResponseHandler<Response, R> callback) {
		if (request.header("Authorization") == null && accessToken != null) { // add auth header if it's missing and we have an accesstoken
			request = request.newBuilder()
					.header("Authorization", getAccessToken())
					.build();
		}
		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				throw new IOException("Unexpected response: " + response 
						+ "\n with body: " + response.body().string());
			}
			return callback.apply(response);
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String getAccessToken() {
		return "Bearer " + accessToken;
	}
	
	@FunctionalInterface
	private interface ResponseHandler<T, R> {
		
		R apply(T t) throws IOException, JSONException;
		
	}
	
	private class UniFlyAuthenticator implements Authenticator {
		
		@Override
		public Request authenticate(Route route, Response response) throws IOException {
			if (response.request().header("Authorization") != null) {
				return null; // give up, we've already failed to authenticate
			}
			String credentials = Credentials.basic("", "");
			
			RequestBody body = new FormBody.Builder()
					.addEncoded("username", "")
					.addEncoded("password", "")
					.addEncoded("grant_type", "password")
					.build();
			
			Request authRequest = new Request.Builder()
					.url(baseUrl + "/oauth/token")
					.header("Authorization", credentials)
					.header("Accept", "application/json")
					.post(body)
					.build();
			
			accessToken = request(authRequest, (authResponse) -> {
				JSONObject authResponseBody = new JSONObject(authResponse.body().string());
				return authResponseBody.getString("access_token");
			});
			return response.request().newBuilder()
						.header("Authorization", getAccessToken())
						.build();
		}
		
	}
	
}
