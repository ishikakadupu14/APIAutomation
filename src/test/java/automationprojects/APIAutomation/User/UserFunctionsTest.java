package automationprojects.APIAutomation.User;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automationprojects.APIAutomation.ReusableComponents.RequestSpecifications;
import automationprojects.APIAutomation.ReusableComponents.ResponseSpecifications;
import automationprojects.APIAutomation.ReusableComponents.ResponseUtils;
import automationprojects.APIAutomation.TestBase.TestCaseBase;

public class UserFunctionsTest extends TestCaseBase {

	HashMap<String, String> data = new HashMap<>();
	String token = null;

	@BeforeMethod
	public void getCreds() {

		data.put("email", "");
		data.put("password", "");
	}

	@Test
	public void loginUserTest() {

		String response = given().spec(RequestSpecifications.postRequestSpecification(data)).when().post("/auth/login").then()
				.spec(ResponseSpecifications.getResponseSpecification(200)).extract().response().asString();
		token = "Bearer " + ResponseUtils.getKeyValue(response, "token");
		Assert.assertTrue(token != null);

	}

	@Test(dependsOnMethods = { "loginUserTest" })
	public void GetCurrentUserTest() {
		String response = given().log().all().spec(RequestSpecifications.getRequestSpecification())
				.header("Authorization", token).when().get("/auth/me").then()
				.spec(ResponseSpecifications.getResponseSpecification(200)).log().all().extract().response().asString();
		Assert.assertEquals(ResponseUtils.getKeyValue(response, "user.email"), data.get("email"));

	}

}
