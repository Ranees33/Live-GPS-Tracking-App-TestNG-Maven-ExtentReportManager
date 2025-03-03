package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClass.Common_functions;
import extentReports.ExtentReportManager;
import pageObjects.Alert_func_page;


@Listeners(Listener.class)
public class Alert_func_test extends Common_functions {
	
	Alert_func_page alertpage;
	ExtentTest test;
	WebDriverWait wait;
	
	@Test(
			priority = 1,
			groups = {"smoke"},
			dependsOnGroups = "gpsdevice",
			retryAnalyzer = RetryAnalyzer.class)
	public void test_alertypefunc() {
		ExtentReportManager.startTest(
				"Validating alert type functionality & the values in Alert Page", 
				"Ranees", 
				"Smoke & Functional Testing");
		
		test = ExtentReportManager.getTest	();
		
		// Navigating to Alerts Page
		alertpage = new Alert_func_page(driver);
		try {
			alertpage.clickalertpageLink.click();
			boolean isalertypeEnabled = alertpage.alertypeleClick.isEnabled();
			if (isalertypeEnabled) {
				test.pass("Alert Type element is enabled on the webpage");
			} else {
				test.fail("Alert Type element is not enabled");

			}
			
			alertpage.alertypeleClick.click();
			List<WebElement> alertType = alertpage.alertypefuncEle;
			for (WebElement iteratealertElement : alertType) {
				System.out.println("Alert Type Element Values: \n" + iteratealertElement.getText() + "\n");
				test.info("Alert Type Element Values: " + iteratealertElement.getText());
			}
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Test failed due to: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
		@Test(
				priority = 2,
				groups = {"smoke"},
				dependsOnGroups = "gpsdevice",
				retryAnalyzer = RetryAnalyzer.class)
		public void test_selectdevicefunc() {
			ExtentReportManager.startTest(
					"Validating select device functionality in Alert Page", 
					"Ranees", 
					"Smoke & Functional Testing");
			
			test = ExtentReportManager.getTest();
			
			alertpage = new Alert_func_page(driver);
			
			try {
				// Using Explicit Wait Method to Interact
				wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.elementToBeClickable((alertpage.selectdeviceEle))).click();
				List<WebElement> selectdeviceValues = alertpage.selectdeviceleValues;
				for (WebElement iterateseledevElement : selectdeviceValues) {
					test.info("Select Device Element Values: \n" + iterateseledevElement.getText() + "\n");
					
				}
				test.log(Status.INFO, "Alert Page functionality test are Passed");
				
			} catch (Exception e) {
				test.log(Status.FAIL, "Test failed due to: " + e.getMessage());
			}

	}

}
