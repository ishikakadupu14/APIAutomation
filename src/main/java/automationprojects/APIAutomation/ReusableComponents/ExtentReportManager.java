package automationprojects.APIAutomation.ReusableComponents;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;


public class ExtentReportManager implements ITestListener {

	public String logFilePath;
	public PrintStream logStream;
	static ExtentReports reports;	
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    
    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }
	
	public static ExtentReports config()
	{
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss");
		ExtentSparkReporter reportsConfig = new ExtentSparkReporter("target/Reports/ExtentReport_"+sdf.format(today)+".html");
		reportsConfig.config().setReportName("API Report");
		reportsConfig.config().setDocumentTitle("API");
		reports = new ExtentReports();
		reports.attachReporter(reportsConfig);
		return reports;
	}
	
	@BeforeMethod
	public void setup(Method method) throws FileNotFoundException {
       
        logFilePath = "target/Reports/" + method.getName() + "_logs.txt";
        logStream = new PrintStream(new FileOutputStream(logFilePath));
        RestAssured.filters(
            RequestLoggingFilter.logRequestTo(logStream),
            ResponseLoggingFilter.logResponseTo(logStream)
        );
    }
	
		  
	  @AfterMethod
	public void tearDown(ITestResult result) {
        try {
           
            logStream.close();
            String content = new String(Files.readAllBytes(Paths.get(logFilePath)));
            if (!content.isEmpty()) {
            	getTest().info("<b>API Execution Logs (from txt file):</b>");
            	getTest().info(MarkupHelper.createCodeBlock(content));
            }

            if (result.getStatus() == ITestResult.FAILURE) {
            	getTest().fail("Test Failed: " + result.getThrowable());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
            	getTest().pass("Test Passed");
            }

        } catch (IOException e) {
        	getTest().fail("Could not attach log file: " + e.getMessage());
        }
        reports.flush();
    }	   
	
	  public static void emptyReportsFolder()
	  {
		  File f = new File (System.getProperty("user.dir")+"/target/Reports");
		  if (!f.exists()) {
		        f.mkdirs();
		    }
		  
			  File[] files=f.listFiles();
			  if(files!=null && files.length>10)
			  {
				 for(int i =0;i<files.length;i++)
				 {
					 if (files[i].isFile()) {
			                files[i].delete();
			            }
				 }
			  }
		  }
	  }

