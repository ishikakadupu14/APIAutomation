package automationprojects.APIAutomation.Events;

import static io.restassured.RestAssured.given;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automationprojects.APIAutomation.PageObjects.EventData;
import automationprojects.APIAutomation.PageObjects.EventsResponse;
import automationprojects.APIAutomation.ReusableComponents.CustomAssertions;
import automationprojects.APIAutomation.ReusableComponents.JsonUtils;
import automationprojects.APIAutomation.Specifications.RequestSpecifications;
import automationprojects.APIAutomation.Specifications.ResponseSpecifications;
import automationprojects.APIAutomation.TestBase.TestCaseBase;
import automationprojects.APIAutomation.User.UserFunctionsTest;
import io.restassured.response.Response;

import java.text.SimpleDateFormat;

public class EventManagementTest extends TestCaseBase {

	EventData event = new EventData();
	CustomAssertions CustomAssertions = new CustomAssertions();

	@Test
	public void createEventTest() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		event.setTitle("Technical Summit AP");
		event.setDescription("A premier technology conference.");
		event.setCategory("Conference");
		event.setVenue("Vizag International Centre");
		event.setCity("Vizag");
		event.setEventDate(sdf.format(new Date()));
		event.setPrice((float) 1500.0);
		event.setTotalSeats(10);
		event.setImageURL("https://example.com/banner.jpg");

		EventsResponse obj = given().spec(RequestSpecifications.postEventRequestSpecification(event, token)).when()
				.post("/events").then().spec(ResponseSpecifications.getResponseSpecification(201)).extract().response()
				.as(EventsResponse.class);

		Integer id = obj.getData().getId();
		CustomAssertions.assertTrue(id != null,"Verify Id is not null");
		String message = obj.getMessage();
		CustomAssertions.assertEquals(message, "Event created successfully","Verify event creation message is displayed");

	}

	@Test
	public void listEventTest() {

		Response response = given().spec(RequestSpecifications.getEventRequestSpecification(token)).when()
				.get("/events").then().spec(ResponseSpecifications.getResponseSpecification(200)).extract().response();
				List<EventData> events = response.jsonPath().getList("data", EventData.class);
		events.forEach(obj->CustomAssertions.assertTrue(obj.getId() != null,"Verify Id is not null"));

	}
}
