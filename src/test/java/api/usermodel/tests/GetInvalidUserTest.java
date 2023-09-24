package api.usermodel.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import io.restassured.response.Response;

public class GetInvalidUserTest {

	@Test
	public void getNonExistentUserTest() {
		Response res = UserEndpoints.getUser(new Faker().name().username());
		Assert.assertEquals(res.getStatusCode(), 404);
		Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 404 Not Found");
		Reporter.log("[Assertion Passed] : Get user with non-existent name results 404 not found");
	}
}
