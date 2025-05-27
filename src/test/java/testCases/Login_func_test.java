package testCases;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import baseClass.Common_functions;
import pageObjects.Login_func_page;
import extentReports.ExtentReportManager;
import testCases.Excel_data_code;

@Listeners(Listener.class)
public class Login_func_test extends Common_functions {

	// Initialize the page object and base class for extent report
	Login_func_page loginPage;
	WebDriver driver;
	WebDriverWait wait;
	ExtentTest test;

	@Test(
		    priority = 1, 
			groups = {"regression", "login"}, 
			dataProvider = "logintestData1", 
			dataProviderClass = Excel_data_code.class, 
			retryAnalyzer = RetryAnalyzer.class
			)
	public void testValidLoginLogoutFunc(String eMail, String pWord, String expected_Result) {
		
        driver = Common_functions.getDriver(); // Get thread-safe driver
		
		System.out.println("Starting the login functionality test...");  // Debug log

		ExtentReportManager.startTest(
				"Login Functionality Test Method initiated (Positive Scenarios)", 
				"Ranees", 
				"Regression Testing");
		test = ExtentReportManager.getTest();
		
		System.out.println("Login functionality test has started successfully..");  // Debug log
		
		try {
			loginPage = new Login_func_page(driver);
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			
			performLogin(eMail, pWord);
			verifyvalidloginResult(expected_Result);
			performLogout();
			
			test.info("'Login' and 'Logout' Test Method Passed");
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Test failed due to: " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("Test Login and Logout Function Method " + Thread.currentThread().getId());
	}
	
	// Perform login steps
	private void performLogin(String eMail, String pWord) {
		test.info("Navigated to tracker.vmmaps.com");
		driver.navigate().refresh();
		loginPage.clickloginEle.click();
		loginPage.enterEmailid.sendKeys(eMail);
		loginPage.enterPsword.sendKeys(pWord);
		test.info("Users enter the email and password with valid data");
		loginPage.submitloginBtn.click();
		test.info("User successfully logged in");

	}

//			 Verify the results using Hard assertions and try catch block!
	private void verifyvalidloginResult(String expected_Result) {
		try {
			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[contains(text(),'" + expected_Result + "')]")));
			Assert.assertTrue(driver.getPageSource().contains(expected_Result));
			test.pass("Expected Results found. Validation passed");

		} catch (Exception e) {
			Assert.fail("Expected result '" + expected_Result + "' not found in page source");
			System.out.println("Assertion Falied");
			test.fail("Expected Results not found. Validation failed");

		}
	}

	// Perform Logout actions
	private void performLogout() {
		try {
			JavascriptExecutor scrolltoProfile = (JavascriptExecutor) driver;
			scrolltoProfile.executeScript("arguments[0].click()",
					wait.until(ExpectedConditions.elementToBeClickable(loginPage.clickuserProfile)));
			loginPage.clicklogoutEle.click();
			test.info("'Login' and 'Logout' Test Method Passed");
			
		} catch (Exception e) {
			test.fail("Elements not clickable or logout action could not be performed");
			throw e;
		}
	}
	
	
	@Test(
			priority = 2,
			groups = {"regression", "login"}, 
			dataProvider = "logintestData2", 
			dataProviderClass = Excel_data_code.class, 
			retryAnalyzer = RetryAnalyzer.class
			)
	public void testInvalidLoginFunc(String eMail, String pWord, String expected_Result) {
		
		driver = Common_functions.getDriver(); // Get thread-safe driver
		
		ExtentReportManager.startTest(
				"Login Functionality Test Method initiated (Negative Scenarios)", 
				"Ranees", 
				"Regression Testing");
		test = ExtentReportManager.getTest();
		
		try {
			loginPage = new Login_func_page(driver);
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			
			invalidperformLogin(eMail, pWord);
			verifyinvalidloginResults(expected_Result);
			
			test.log(Status.INFO, "All 'Login' Test Method with invalid test data are Passed");
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Test failed due to: " + e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("Test Login Function Method " + Thread.currentThread().getId());
		
	}

		private void invalidperformLogin(String eMail, String pWord) {
			try {
				driver.navigate().refresh();
				// Perform login steps
				loginPage.enterEmailid.clear();
				loginPage.enterEmailid.sendKeys(eMail);
				loginPage.enterPsword.clear();
				loginPage.enterPsword.sendKeys(pWord);
				test.info("Users enter the email and password with invalid data");
				loginPage.submitloginBtn.click();
			} catch (Exception e) {
				test.fail(e.getMessage());
					
				}
		}
	
		// Verify the results using Hard assertions and try catch block!
		private void verifyinvalidloginResults(String expected_Result) {
			try {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(text(),'" + expected_Result + "')]")));
				Assert.assertTrue(driver.getPageSource().contains(expected_Result));
				System.out.println("Assertion Passed");
				test.pass("Expected Results found. Validation passed");

			} catch (Exception e) {
				Assert.fail("Expected result '" + expected_Result + "' not found in page source");
				System.out.println("Assertion Falied");
				test.fail("Expected Results not found. Validation failed");

			}
		}
	}
