package api.usermodel.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.UserPOJO;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	UserPOJO reqPayload;
	Logger log = LogManager.getLogger(UserTests.class);

	@BeforeClass
	public void setUp() {
		faker = new Faker();
		reqPayload = new UserPOJO();
		reqPayload.setId(faker.random().nextInt(1000, 300000));
		reqPayload.setUsername(faker.name().username());
		reqPayload.setFirstName(faker.name().firstName());
		reqPayload.setLastName(faker.name().lastName());
		reqPayload.setEmail(faker.internet().emailAddress());
		reqPayload.setPassword(faker.internet().password());
		reqPayload.setPhone(faker.phoneNumber().cellPhone());
		reqPayload.setUserStatus(1);
		
		log.info("Created POJO object using unique info with faker");
		log.debug("test debug");
	}

	@Test(priority = 1)
	public void createUserTest() {

		Reporter.log(" ******************  Testing create User *************", true);
		Response res = UserEndpoints.createUser(reqPayload);
		Assert.assertTrue(res.getStatusCode() == 200);
		Reporter.log("[Assertion Passed]: Created user with name: " + reqPayload.getUsername(), true);
		log.info("Create user test pass");
	}

	@Test(priority = 2)
	public void getUser() {
		Reporter.log(" ******************  Testing get User *************", true);

		Response res = UserEndpoints.getUser(reqPayload.getUsername());
		Assert.assertTrue(res.getStatusCode() == 200);
		Assert.assertTrue(res.jsonPath().getString("username").equals(reqPayload.getUsername()));

		res.prettyPrint();
		Reporter.log("[Assertion Passed]: Get user with name: " + reqPayload.getUsername(), true);
	}

	@Test(priority = 3)
	public void updateUser() {
		Reporter.log(" ******************  Testing update User *************", true);

		String updatePhone = faker.phoneNumber().cellPhone();
		String updateEmail = faker.internet().emailAddress();

		reqPayload.setPhone(updatePhone);
		reqPayload.setEmail(updateEmail);

		Response res = UserEndpoints.updateUser(reqPayload.getUsername(), reqPayload);
		Assert.assertTrue(res.getStatusCode() == 200);


		Response res2 = UserEndpoints.getUser(reqPayload.getUsername());
		Assert.assertTrue(res2.jsonPath().getString("phone").equals(updatePhone));
		Assert.assertTrue(res2.jsonPath().getString("email").equals(updateEmail));
		
		res2.prettyPrint();
		Reporter.log("[Assertion Passed]: Updated user with name: " + reqPayload.getUsername(), true);
	}

	@Test(priority = 4)
	public void deleteUser() throws InterruptedException {
		Reporter.log(" ******************  Testing delete User *************", true);

		Response res = UserEndpoints.deleteUser(reqPayload.getUsername());
		Assert.assertTrue(res.getStatusCode() == 200);
		
		Thread.sleep(2000);

		Response res2 = UserEndpoints.getUser(reqPayload.getUsername());
		Assert.assertTrue(res2.getStatusCode() == 404);
		Reporter.log("[Assertion Passed]: Deleted user with name: " + reqPayload.getUsername(), true);
	}
}
