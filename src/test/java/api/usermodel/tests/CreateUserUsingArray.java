package api.usermodel.tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.UserPOJO;
import api.utilities.DataProviders;
import api.utilities.ExcelFileUtility;
import io.restassured.response.Response;

public class CreateUserUsingArray {

	ExcelFileUtility eUTils;
	int userObjectsCount;

	UserPOJO[] usersData;
	int index = 0;

	@BeforeClass
	public void setUp() throws EncryptedDocumentException, IOException {
		eUTils = new ExcelFileUtility();
		userObjectsCount = eUTils.getRowCount("UserModel");

		usersData = new UserPOJO[userObjectsCount];
	}

	@Test(dataProvider = "getAllUserData", dataProviderClass = DataProviders.class)
	public void createArrayPayload(String id, String username, String firstName, String lastName, String email,
			String password, String phone, String status) {
		UserPOJO obj = new UserPOJO();
		obj.setId(Integer.parseInt(id));
		obj.setUsername(username);
		obj.setFirstName(firstName);
		obj.setLastName(lastName);
		obj.setEmail(email);
		obj.setPassword(password);
		obj.setPhone(phone);
		obj.setUserStatus(Integer.parseInt(status));

		usersData[index++] = obj;
	}

	@Test(dependsOnMethods = "createArrayPayload")
	public void createUsers() {
		Reporter.log(" ******************  Testing create users using array *************", true);
		Response res = UserEndpoints.createUserUsingArray(usersData);
		Assert.assertEquals(res.getStatusCode(), 200);
		Reporter.log("[Assertion Passed]: Created users using array", true);
	}

	@Test(dependsOnMethods = "createUsers", dataProvider = "getAllUsernames", dataProviderClass = DataProviders.class)
	public void getUsersTest(String userName) {
		Reporter.log(" ******************  Testing get user (created using array) *************", true);
		Response res = UserEndpoints.getUser(userName);
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.jsonPath().getString("username"), userName);
		
		res.prettyPrint();
		Reporter.log("[Assertion Passed]: Got user using array with name : " + userName, true);
	}
	

	@Test(dependsOnMethods = "getUsersTest", dataProvider = "getAllUsernames", dataProviderClass = DataProviders.class)
	public void deleteUserTest(String userName) {
		Reporter.log(" ******************  Testing delete user (created using array) *************", true);
		Response res = UserEndpoints.deleteUser(userName);
		Assert.assertEquals(res.getStatusCode(), 200);

		Response res2 = UserEndpoints.getUser(userName);
		Assert.assertEquals(res2.getStatusCode(), 404);
		
		Reporter.log("[Assertion Passed]: Deleted user using array with name : " + userName, true);
	}
	
}
