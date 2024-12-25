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

public class CustomTestListener implements ITestListener {

    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    private ExtentTest test;
    private String reportName;

    // Method that runs when a test starts
    @Override
    public void onStart(ITestContext itestcontext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "TestReport_" + timeStamp + ".html"; // Report file name with timestamp

        // Dynamically set the report path to a user-defined folder or a relative path
        String reportPath = System.getProperty("user.dir") + "/TestReports/" + reportName;

        // Initializing ExtentSparkReporter with the report file path
        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("VMS Web Login Test Report");
        sparkReporter.config().setTheme(Theme.DARK);

        // Initializing ExtentReports and attaching the reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "VMS");
        extent.setSystemInfo("Application Module", "Login");
        extent.setSystemInfo("SubModule", "VMS Web Login");
        extent.setSystemInfo("UserName", System.getProperty("user.name"));

        // Capture OS and Browser details from test parameters (configurable in testng.xml)
        String os = itestcontext.getCurrentXmlTest().getParameter("OS");
        extent.setSystemInfo("OS", os != null ? os : "Not Provided");

        String browser = itestcontext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser != null ? browser : "Not Provided");

        List<String> includedGroups = itestcontext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        } else {
            extent.setSystemInfo("Groups", "No Groups Defined");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName() + " - " + result.getName());
        test.assignCategory(result.getMethod().getGroups());
        String parameters = result.getParameters().length > 0 ? result.getParameters().toString() : "No Parameters";
        test.log(Status.PASS, "Test Passed with parameters: " + parameters);
        test.log(Status.PASS, "Execution Time: " + getTestExecutionTime(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName() + " - " + result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, "Test Failed with message: " + result.getThrowable().getMessage());

        // Capture screenshot for failed tests
        try {
            String imagePath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imagePath);
        } catch (IOException e) {
            test.log(Status.FAIL, "Error while capturing screenshot: " + e.getMessage());
        }

        // Log execution time
        test.log(Status.INFO, "Execution Time: " + getTestExecutionTime(result));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName() + " - " + result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test Skipped with reason: " + result.getThrowable().getMessage());
        test.log(Status.INFO, "Execution Time: " + getTestExecutionTime(result));
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();  // Write the test results to the report

        // Send email notification with the report (Optional)
        sendEmailNotification(context);
    }

    private String getTestExecutionTime(ITestResult result) {
        long startTime = result.getStartMillis();
        long endTime = result.getEndMillis();
        long duration = endTime - startTime;
        return formatDuration(duration);
    }

    private String formatDuration(long duration) {
        long seconds = (duration / 1000) % 60;
        long minutes = (duration / (1000 * 60)) % 60;
        long hours = (duration / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    // Method to send email notification with the report link (optional integration)
    private void sendEmailNotification(ITestContext context) {
        // Code to send an email notification with the generated report (e.g., using JavaMail API)
    }
}
