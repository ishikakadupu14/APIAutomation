package automationprojects.APIAutomation.TestBase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class TestCaseBase {
	@BeforeSuite
	public void setupLogging() throws FileNotFoundException {
	    PrintStream log = new PrintStream(new FileOutputStream("logging.txt"), true);
	    RestAssured.filters(new RequestLoggingFilter(log), new ResponseLoggingFilter(log));
	}
	
}
