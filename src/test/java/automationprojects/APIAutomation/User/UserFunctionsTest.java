package automationprojects.APIAutomation.User;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automationprojects.APIAutomation.ReusableComponents.CustomAssertions;
import automationprojects.APIAutomation.ReusableComponents.JsonUtils;
import automationprojects.APIAutomation.Specifications.RequestSpecifications;
import automationprojects.APIAutomation.Specifications.ResponseSpecifications;
import automationprojects.APIAutomation.TestBase.TestCaseBase;

public class UserFunctionsTest extends TestCaseBase {
		
	@Test
	public void GetCurrentUserTest() {
		String response = given().log().all().spec(RequestSpecifications.getRequestSpecification())
				.header("Authorization", token).when().get("/auth/me").then()
				.spec(ResponseSpecifications.getResponseSpecification(200)).log().all().extract().response().asString();
		tearDown();
		CustomAssertions customAssertions = new CustomAssertions();
		String email= System.getProperty("email");
		customAssertions.assertEquals(JsonUtils.getKeyValue(response, "user.email"), email, "Verify email of the current logged in user");

	}

}
