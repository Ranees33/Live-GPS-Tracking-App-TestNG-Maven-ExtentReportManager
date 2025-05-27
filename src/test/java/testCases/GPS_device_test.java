package testCases;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClass.Common_functions;
import extentReports.ExtentReportManager;
import pageObjects.GPS_device_page;

@Listeners(Listener.class)
public class GPS_device_test extends Common_functions {
	
	GPS_device_page gpsdevice;
	WebDriver driver;
	ExtentTest test;
	
	@Test(
			priority = 1, 
			groups = {"functional", "gpsdevice"}, 
			dependsOnGroups = "dashboard", 
			retryAnalyzer = RetryAnalyzer.class)
	public void test_gpsdevicepage_elements() {
		
        driver = Common_functions.getDriver(); // Get thread-safe driver
		
		ExtentReportManager.startTest("GPS Device Page Elements Visible Verification", "Ranees", "Functional Testing");
		test = ExtentReportManager.getTest();
		
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
		System.out.println("\n" + "Test GPS Device Page Elements Validation Method " + Thread.currentThread().getId());
		
	}
	
	@Test(
			priority = 2, 
			groups = {"functional", "gpsdevice"}, 
			dependsOnGroups = "dashboard", 
			retryAnalyzer = RetryAnalyzer.class)
	public void test_gpsdevicepage_printelementsInfo() {
		
        driver = Common_functions.getDriver(); // Get thread-safe driver

		ExtentReportManager.startTest("GPS Device Page Pop up Window Page Elements Info Validation", "Ranees", "Functional Testing");
		test = ExtentReportManager.getTest();
		
		gpsdevice = new GPS_device_page(driver);
		
		// Navigate to the gps add device pop up page
		test.info("Navigated to pop up window");
		gpsdevice.clickaddeviceBtn.click();
		test.info("Checking elements info on Add Device Pop Up Window and print it in console");
		List<WebElement> elementsInfo = gpsdevice.printelementsInfo;
		for (WebElement iterateElement : elementsInfo) {
			System.out.println("Print Elements Info: " + iterateElement.getText());
			
		}
		gpsdevice.clickcancelBtn.click();
		
		test.log(Status.INFO, "GPS Add Device Pop Up Page Elements Info Validation Test Passed");
		System.out.println("Test GPS Add Device Pop Up Page Elements Info Print Method " + Thread.currentThread().getId());
	}

}
