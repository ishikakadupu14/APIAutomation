package automationprojects.APIAutomation.ReusableComponents;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import automationprojects.APIAutomation.TestBase.TestCaseBase;

public class TestListeners implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		ExtentTest test = ExtentReportManager.reports.createTest(result.getMethod().getMethodName());
		ExtentReportManager.setTest(test);
		test.info(MarkupHelper.createLabel("Test Started", ExtentColor.BROWN));
		//ExtentReportManager.config();
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		ExtentReportManager.getTest().info(MarkupHelper.createLabel("Test Completed", ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		ExtentReportManager.getTest().fail("Test Failed: " + result.getThrowable());
		ExtentReportManager.getTest().info(MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		ExtentReportManager.getTest().info(MarkupHelper.createLabel("Test Skipped", ExtentColor.ORANGE));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
		ExtentReportManager.config();
		ExtentReportManager.emptyReportsFolder();
		TestCaseBase base= new TestCaseBase();
		base.createToken();
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		ExtentReportManager.reports.flush();
	}

}
