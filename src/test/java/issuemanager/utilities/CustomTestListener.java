package issuemanager.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import issuemanagertestcases.BaseClass;

public class CustomTestListener implements ITestListener 
{

    ExtentSparkReporter sparkReporter;
    ExtentReports extent;
    ExtentTest test;
    String reportName;

    // Method that runs when a test starts
    @Override
    public void onStart(ITestContext itestcontext)
    {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // Correct date format
        reportName = "TestReport" + timeStamp + ".html"; // Append timestamp to the report file name
        
        // Initializing ExtentSparkReporter with the report file path
        sparkReporter = new ExtentSparkReporter("C:\\Users\\DELL\\eclipse-workspace\\FrameWorkFromScratch\\TestReport\\" + reportName);
        sparkReporter.config().setDocumentTitle("FrameWorkFromScratch");
        sparkReporter.config().setReportName("FrameWorkFromScratchTestReport");
        sparkReporter.config().setTheme(Theme.DARK); // Setting the theme
        
        // Initializing ExtentReports and attaching the reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application","VMS" );
        extent.setSystemInfo("Application Module","Login" );
        extent.setSystemInfo("SubModule","VMSwebLogin");
        extent.setSystemInfo("UserName", System.getProperty("user.name"));
        String os=itestcontext.getCurrentXmlTest().getParameter("OS");
        extent.setSystemInfo("OS", os);
        String browser=itestcontext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);
        List<String>incluedgroups=itestcontext.getCurrentXmlTest().getIncludedGroups();
        if(!incluedgroups.isEmpty()) 
        {
        	 extent.setSystemInfo("Groups",incluedgroups.toString());
        }else 
        {
        	System.out.println("No Groups Available!!!!");
        }
        
        
		 }
    @Override
    public void onTestSuccess(ITestResult results) 
    {
    	test=extent.createTest(results.getTestClass().getName());
    	test.assignCategory(results.getMethod().getGroups());
    	test.log(Status.PASS,results.getName()+"Test Pass");
        
    }
    
    
    @Override
    public void onTestFailure(ITestResult results) 
    {
    	test=extent.createTest(results.getTestClass().getName());
    	test.assignCategory(results.getMethod().getGroups());
    	test.log(Status.FAIL,results.getName()+"Test Pass");
    	test.log(Status.INFO,results.getThrowable().getMessage());
    	
    	try 
    	{
    		String imagepath=new BaseClass().captureScreen(results.getName());
    		test.addScreenCaptureFromPath(imagepath);
    	} catch(IOException el) 
    	{
    		el.printStackTrace();
    	}
        
    	
    	
    }
    
    @Override
    public void onTestSkipped(ITestResult results) 
    {
    	test=extent.createTest(results.getTestClass().getName());
    	test.assignCategory(results.getMethod().getGroups());
    	test.log(Status.SKIP,results.getName()+"Test Skipped");
    	test.log(Status.INFO,results.getThrowable().getMessage());
        
    }
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

}
