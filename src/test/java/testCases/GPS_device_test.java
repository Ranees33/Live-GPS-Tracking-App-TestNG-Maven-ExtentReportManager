package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClass.Common_functions;
import extentReports.ExtentReportManager;
import pageObjects.GPS_device_page;

public class GPS_device_test extends Common_functions {
	
	GPS_device_page gpsdevice;
	ExtentTest test;
	
	@Test(priority = 1, groups = {"functional", "gpsdevice"}, dependsOnGroups = "dashboard", retryAnalyzer = RetryAnalyzer.class)
	public void test_gpsdevicepage_elements() {
		test = ExtentReportManager.extentReport.createTest("GPS Device Page Elements Visible Verification");
		test.assignAuthor("Ranees");
		test.assignCategory("Functional Testing");
		
		gpsdevice = new GPS_device_page(driver);
		
		// Navigate to the gps device page
		test.info("Navigated to GPS device page");
		gpsdevice.navigategpsPage.click();
		
		// Check if the button is displayed and visible to users
		test.info("Verify the button elements are visible in GPS Device Page");
		boolean isaddevicebtnDisplayed = gpsdevice.visibleadd_devicebtnEle.isDisplayed();
		if (isaddevicebtnDisplayed) {
			test.pass("Button is displayed on the webpage.");	
		} else {
			test.fail("Button exists but is not visible on the webpage.");

		}
		
		test.log(Status.INFO, "GPS Device Page Elements Validation Test Passed");
		System.out.println("Test GPS Device Page Elements Validation Method " + Thread.currentThread().getId());
		
	}

}
