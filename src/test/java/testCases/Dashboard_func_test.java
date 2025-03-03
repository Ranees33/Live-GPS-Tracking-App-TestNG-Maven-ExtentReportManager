package testCases;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClass.Common_functions;
import extentReports.ExtentReportManager;
import pageObjects.Dashboard_func_page;

@Listeners(Listener.class)
public class Dashboard_func_test extends Common_functions {
	
	Dashboard_func_page dashboard;
	ExtentTest test;
	
	@Test(
			priority = 1, 
			groups = {"functional", "dashboard"}, 
			dependsOnGroups = "login", 
			dataProvider = "logintestData1", 
			dataProviderClass = Excel_data_code.class, 
			retryAnalyzer = RetryAnalyzer.class)
	public void test_dashboardfunc_profilenameverify(String eMail, String pWord, String expected) {
		
		ExtentReportManager.startTest("Dashboard Page User Profile Name Validation", "Ranees", "Functional Testing");
		test = ExtentReportManager.getTest();
		
		dashboard = new Dashboard_func_page(driver);
		
		driver.navigate().refresh();
		dashboard.enterEmailid.sendKeys(eMail);
		dashboard.enterPsword.sendKeys(pWord);
		dashboard.enterPsword.sendKeys(Keys.TAB, Keys.ENTER);
		test.info("Users enter the email and password with valid data");
		String verifyprofileText = dashboard.verifyprofileText.getText();
		
		// Assert to ensure the validation directly that the profile name is "HiArkranees"
		Assert.assertTrue(verifyprofileText.contains("ranees"), "Profile name does not contain 'ranees'");
		
		// Alternatively Use the condition in the if-else
		if (verifyprofileText.contains("ranees")) {
			test.pass("Profile name contains 'ranees': " + verifyprofileText);
		} else {
			test.fail("Profile name does not contain 'ranees': " + verifyprofileText);
			
		}
		
		test.log(Status.INFO, "Dashboard Profile Name Validation Test Passed");
		System.out.println("Test Dashboard Profile Verify Method " + Thread.currentThread().getId());
	}
	
	@Test(
			priority = 2, 
			groups = {"functional", "dashboard"}, 
			dependsOnGroups = "login", 
			retryAnalyzer = RetryAnalyzer.class)
	public void test_dashboard_livetrackingverify() {
		
		// test = ExtentReportManager.extentReport.createTest("Dashboard Live Tracking Status Visible Validation");
		// test.assignAuthor("Ranees");
		// test.assignCategory("Functional Testing");
		
		ExtentReportManager.startTest("Dashboard Live Tracking Status Visible Validation", "Ranees", "Functional Testing");
		test = ExtentReportManager.getTest();
		
		String expectedText = "No Devices Found";
		dashboard.clicklivetrackingMenu.click();
		test.info("Navigate to the tracking page");
		String verifyliveTracking = dashboard.verifynodevicefoundText.getText();

		// Use the condition in the if-else
		if (verifyliveTracking.equals(expectedText)) {
			test.pass("Live tracking status are equal: " + verifyliveTracking);
		} else {
			test.fail("Live tracking status are not equal: " + verifyliveTracking);
			// Reporter.log("Live tracking status are not equal: " + verifyliveTracking);   // This reporter.log for testNG Report!

		}

		// Alternatively, use assert that the status are no device found for live tracking
		Assert.assertEquals(verifyliveTracking, expectedText, "Actual text doesn't match the expected text!");
		
		test.log(Status.INFO, "Dashboard Page Live Tracking Status Verification Test Passed");
		System.out.println("Test Dashboard Live Tracking Verify Method " + Thread.currentThread().getId());


	}

}
