package automationprojects.APIAutomation.TestBase;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import automationprojects.APIAutomation.ReusableComponents.ExtentReportManager;
import automationprojects.APIAutomation.ReusableComponents.JsonUtils;
import automationprojects.APIAutomation.Specifications.RequestSpecifications;
import automationprojects.APIAutomation.Specifications.ResponseSpecifications;

public class TestCaseBase extends ExtentReportManager {
	public HashMap<String, String> data = new HashMap<>();

	public static String token = null;
	
	
	@BeforeMethod
	public String getToken() {
		
		data.put("email",System.getProperty("email"));
		data.put("password",System.getProperty("password") );
		
		String response = given().spec(RequestSpecifications.postRequestSpecification(data)).when().post("/auth/login")
				.then().spec(ResponseSpecifications.getResponseSpecification(200)).extract().response().asString();
		token = "Bearer " + JsonUtils.getKeyValue(response, "token");
		//CustomAssertions.assertTrue(token != null, "Verify Bearer token is generated");
		return token;
	}

}
