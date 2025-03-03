package testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import extentReports.ExtentReportManager;

public class Listener implements ITestListener{
	
	@Override
    public void onTestStart(ITestResult result) {
        // No implementation needed here
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Retrieve the WebDriver instance from the test context
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
        if (driver != null) {
            // Capture screenshot
            String screenshotPath = ExtentReportManager.captureScreenshot(driver, result.getName());
            // Attach screenshot to Extent Report
			ExtentReportManager.getTest().log(Status.FAIL, "Test Failed. Screenshot:",
			        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }

    
    // Other overridden methods from ITestListener...

    @Override
    public void onStart(ITestContext context) {
        // Initialize Extent Reports at the start of the test context
        ExtentReportManager.setupExtentReports();
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush the Extent Reports at the end of the test context
        ExtentReportManager.flushReport();
    }
}
		
	
	
	
	
	
	

	// Please Note: If need to save the failed test screenshot in project folder, we can use the below one.
	
//	@Override
//    public void onTestStart(ITestResult result) {
//        driver = (WebDriver) result.getTestContext().getAttribute("driver");
//    }
//
//
//	@Override
//	public void onTestFailure(ITestResult result) {
//		// Capture screenshot on test failure
//		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		try {
//			// Save the screenshot to a desired location
//			FileUtils.copyFile(screenshot, new File("screenshots/" + result.getName() + ".png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

    // Implement other ITestListener methods as needed

