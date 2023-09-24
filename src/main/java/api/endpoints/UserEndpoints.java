package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.UserPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * This class consists of methods for CRUD operations for User Model
 * 
 * @author sharu
 *
 */

public class UserEndpoints {

	public static Response createUser(UserPOJO reqPayload) {
		Response res = given()
			.contentType(ContentType.JSON)
			.body(reqPayload)
		.when()
			.post(Routes.post_user_url);
		
		return res;
	}

	public static Response getUser(String username) {
		Response res = given()
			.pathParam("username", username)
		.when()
			.get(Routes.get_user_url);
		
		return res;
	}

	public static Response deleteUser(String username) {
		Response res = given()
				.pathParam("username", username)
			.when()
				.delete(Routes.delete_user_url);
			
			return res;
	}

	public static Response updateUser(String username, UserPOJO reqPayload) {
		Response res = given()
			.contentType(ContentType.JSON)
			.body(reqPayload)
			.pathParam("username", username)
		.when()
			.put(Routes.update_user_url);
		
		return res;
	}

	public static Response createUserUsingArray(UserPOJO[] reqPayload) {
		Response res = given()
				.contentType(ContentType.JSON)
				.body(reqPayload)
			.when()
				.post(Routes.post_UserArray_url);
			
			return res;
	}
}
