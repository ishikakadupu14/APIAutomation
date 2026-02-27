package automationprojects.APIAutomation.ReusableComponents;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecifications {

	public static RequestSpecification postRequestSpecification(Object body)  {

		RequestSpecification postReqSpec = new RequestSpecBuilder()
				.setBaseUri("https://api.eventhub.rahulshettyacademy.com/api")
				.addHeader("Content-Type", "application/json").setBody(body).build();
		return postReqSpec;
	}

	public static RequestSpecification getRequestSpecification() {

		RequestSpecification getReqSpec = new RequestSpecBuilder()
				.setBaseUri("https://api.eventhub.rahulshettyacademy.com/api").addHeader("accept", "application/json")
				.build();
		return getReqSpec;
	}
}
