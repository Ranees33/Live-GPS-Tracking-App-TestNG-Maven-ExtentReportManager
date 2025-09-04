package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import baseClass.Common_functions;
import extentReports.ExtentReportManager;
import pageObjects.Subscription_page;

@Listeners(Listener.class)
public class Subscription_test {
	
	WebDriver driver;
	Subscription_page subscriptionpage;
	ExtentTest test;
	WebDriverWait wait;
	
	@Test(
			groups = {"smoke"}, 
			dependsOnGroups = "dashboard",
			retryAnalyzer = RetryAnalyzer.class)
	public void testSubsrciptionPage() throws InterruptedException {
		
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
		boolean isBuyNowVisible = subscriptionpage.premiumBuyNowButton.isDisplayed();
		if (isBuyNowVisible) {
			test.pass("'Buy Now' button is displayed on Subscription Page");
		} else {
			test.fail("'Buy Now' button is not displayed on Subscription Page");
			
			Assert.fail("'Buy Now' button is not displayed");
		}
		
		// Verify the Buy Now button that is clickable or not to users
		test.info("Verify the 'Buy Now' button is clickable to users");
		
		// boolean isBuyNowEnabled = subscriptionpage.premiumBuyNowButton.isEnabled();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		boolean isBuyNowEnabled = wait.until(ExpectedConditions.elementToBeClickable(subscriptionpage.premiumBuyNowButton)).isEnabled();
		if (isBuyNowEnabled) {
			test.pass("'Buy Now' button is enabled on Subscription Page");
			subscriptionpage.premiumBuyNowButton.click();
			Thread.sleep(4000);
			test.pass("Buy Now button is clickable");
			
//			try {
//				System.out.println(driver.getCurrentUrl());
//				
//			} catch (Exception e) {
//				System.out.println("Error occured: " + e.getMessage());
//				e.printStackTrace();
//			}
			
		} else {
			test.fail("'Buy Now' button is enabled but not clickable on Subscription Page");
			
			Assert.fail("Buy Now button is not clickable");

		}
		
		test.info("Subscription page validation test passed");
		System.out.println("Executing Test: testSubsrciptionPage | Thread ID: " + Thread.currentThread().getId());
		
		
	}

}
