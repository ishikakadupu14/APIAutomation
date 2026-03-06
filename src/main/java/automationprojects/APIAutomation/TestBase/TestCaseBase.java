package automationprojects.APIAutomation.TestBase;
import static io.restassured.RestAssured.given;
import java.util.HashMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import automationprojects.APIAutomation.ReusableComponents.ExtentReportManager;
import automationprojects.APIAutomation.ReusableComponents.JsonUtils;
import automationprojects.APIAutomation.Specifications.RequestSpecifications;
import automationprojects.APIAutomation.Specifications.ResponseSpecifications;

public class TestCaseBase extends ExtentReportManager {
	public HashMap<String, String> data = new HashMap<>();

	public static String token = setToken();
		
	public void createToken(){
		
		data.put("email",System.getProperty("email"));
		data.put("password",System.getProperty("password") );
		
		String response = given().spec(RequestSpecifications.postRequestSpecification(data)).when().post("/auth/login")
				.then().spec(ResponseSpecifications.getResponseSpecification(200)).extract().response().asString();
		String testtoken = "Bearer " + JsonUtils.getKeyValue(response, "token");
		
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(System.getProperty("user.dir")+"/src/test/resources/Tokens/token.txt"))) {
		    writer.write(testtoken);
		    writer.newLine();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
	}
		public static String setToken()
		{
			String token=null;
			try {
			BufferedReader tokenFileReader=Files.newBufferedReader(Paths.get(System.getProperty("user.dir")+"/src/test/resources/Tokens/token.txt"));
			 token = tokenFileReader.readLine();
			
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
			return token;
		}
				
	}


