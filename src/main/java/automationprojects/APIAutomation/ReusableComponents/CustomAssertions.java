package automationprojects.APIAutomation.ReusableComponents;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import automationprojects.APIAutomation.TestBase.TestCaseBase;

public class CustomAssertions extends TestCaseBase {
	 

	public  void assertTrue(boolean condition, String message) {
		ExtentTest test = ExtentReportManager.getTest();
		test.log(Status.INFO, message);
		try {

			Assert.assertTrue(condition);
			test.log(Status.PASS, "Expected: True , Actual: True");
		} catch (AssertionError e) {

			test.log(Status.FAIL, "Expected: True , Actual: False");
		}
	}

	public void assertEquals(Object actual, Object expected, String message) {
		ExtentTest test = ExtentReportManager.getTest();
		test.log(Status.INFO, message);
		try {
			Assert.assertEquals(actual, expected);
			test.log(Status.PASS, "Expected: " + expected + " , Actual: " + actual);
		} catch (AssertionError e) {

			test.log(Status.FAIL, "Expected: " + expected + " , Actual: " + actual);
		}
	}

}
