package automationprojects.APIAutomation.ReusableComponents;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecifications {

	public static ResponseSpecification getResponseSpecification(int statusCode) {
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(statusCode)
				.expectContentType(ContentType.JSON)
				.build();
		return resSpec;
	}

}
