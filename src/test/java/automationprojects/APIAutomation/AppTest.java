package automationprojects.APIAutomation;




import org.testng.*;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import com.aventstack.extentreports.gherkin.model.Given;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
    	System.out.println("Hi");
    	given().log().all().baseUri("https://www.amazon.com/").when().get().then().log().all().assertThat().statusCode(200).extract().response();
    }
}
