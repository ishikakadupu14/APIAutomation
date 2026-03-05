package automationprojects.APIAutomation.Specifications;

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
	
	public static RequestSpecification postEventRequestSpecification(Object body, String token)  {

		RequestSpecification postReqSpec = new RequestSpecBuilder()
				.setBaseUri("https://api.eventhub.rahulshettyacademy.com/api")
				.addHeader("Authorization", token)
				.addHeader("Content-Type", "application/json").setBody(body).build();
		return postReqSpec;
	}
	public static RequestSpecification getEventRequestSpecification(String token)  {

		RequestSpecification getReqSpec = new RequestSpecBuilder()
				.setBaseUri("https://api.eventhub.rahulshettyacademy.com/api")
				.addHeader("Authorization", token)
				.addHeader("Content-Type", "application/json").build();
		return getReqSpec;
	}
}
