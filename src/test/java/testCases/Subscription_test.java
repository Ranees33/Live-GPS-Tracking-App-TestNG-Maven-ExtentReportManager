package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import baseClass.Common_functions;
import extentReports.ExtentReportManager;
import pageObjects.Subscription_page;

public class Subscription_test {
	
	WebDriver driver;
	Subscription_page subscriptionpage;
	ExtentTest test;
	
	@Test(
			groups = {"smoke"}, 
			dependsOnGroups = "dashboard",
			retryAnalyzer = RetryAnalyzer.class)
	public void testSubsrciptionPage() {
		
		driver = Common_functions.getDriver();
		
		ExtentReportManager.startTest(
				"Subscription page - Element and Functionality validation", 
				"Ranees", 
				"Smoke Testing"
				);
		test = ExtentReportManager.getTest();
		
		subscriptionpage = new Subscription_page(driver);
		
		test.info("Navigate to the subscription page");
		
		test.info("Validate 'Buy Now' button on Subscription page");
		
		subscriptionpage.viewSubscription.click();
		boolean isBuyNowDisplayed = subscriptionpage.premiumBuyNowButton.isDisplayed();
		boolean isBuyNowEnabled = subscriptionpage.premiumBuyNowButton.isEnabled();
		if (isBuyNowDisplayed && isBuyNowEnabled) {
			test.pass("'Buy Now' button is displayed and enabled on Subscription Page");
		} else {
			test.fail("'Buy Now' button is not displayed or enabled on Subscription Page");
			
			Assert.fail("'Buy Now' button is not displayed");
			Assert.fail("'Buy Now' button is not enabled");
		}
		
		test.info("Subscription page validation test passed");
		System.out.println("Executing Test: testSubsrciptionPage | Thread ID: " + Thread.currentThread().getId());
		
	}

}
