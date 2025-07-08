package baseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.remote.RemoteWebDriver;


import extentReports.ExtentReportManager;

public class Common_functions {
	
	// public static WebDriver driver = null;    // Static WebDriver
	
	// Replace static WebDriver with ThreadLocal<WebDriver>
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public static Properties properties = null;
	
	private static final String BROWSERSTACK_URL = "https://raneesm_VPIrbk:EQNBYfCsyYiHyKa2LyGs@hub-cloud.browserstack.com/wd/hub";
	
	public Properties loadPropertyFile() throws IOException {

		FileInputStream fileInputStream = new FileInputStream("config.Properties");
		properties = new Properties();
		properties.load(fileInputStream);

		return properties;
	}
	
	@BeforeSuite(alwaysRun = true)
    public void setupSuite() {
		System.out.println("Starting before suite method.."); // Debug log
		ExtentReportManager.setupExtentReports();
    }
	
	@BeforeTest(alwaysRun = true)
	@Parameters({"browser"})
	public void launch_Browser(ITestContext context, String browser) throws IOException {
		
		loadPropertyFile();
		String browsers = properties.getProperty("browser");
		final String loginpage_url = properties.getProperty("test_Url");
//		final String loginpage_url2 = properties.getProperty("test_Url2");
		

		if (browsers.equalsIgnoreCase("chrome")) {
			// Set up ChromeDriver dynamically using WebDriver Manager
			WebDriverManager.chromedriver().setup();
			
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			
			// W3C-compliant BrowserStack capabilities
			options.setCapability("browserstack:options", Map.of(
					"os", "Windows",
				    "osVersion", "11",
				    "projectName", "VMTrackers GPS Application Test",
				    "buildName", "Page Modules Validation",
				    "sessionName", "Chrome Test"
				 // "local", "true" // Uncomment for internal apps
				));
			
			options.addArguments("disable-notifications");
			options.addArguments("--disable-save-password-bubble"); // Disables the "Save Password" prompt
			options.addArguments("--remote-allow-origins=*");
			// Set preferences to disable the infobar 'Browser controlled by automated tool'
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//			driver.set(new ChromeDriver(options));
            driver.set(new RemoteWebDriver(new URL(BROWSERSTACK_URL), options));
			
		} else if (browsers.equalsIgnoreCase("firefox")) {
			// Set up GeckoDriver dynamically using WebDriver Manager
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			
			// W3C-compliant BrowserStack capabilities
			options.setCapability("browserstack:options", Map.of(
					"os", "Windows",
				    "osVersion", "11",
				    "projectName", "VMTrackers GPS Application Test",
				    "buildName", "Page Modules Validation",
				    "sessionName", "Firefox Test"
				 // "local", "true" // Uncomment for internal apps
				));
			
			// Set preferences to disable the infobar
	        options.addPreference("dom.webdriver.enabled", false);
	        options.addPreference("useAutomationExtension", false);
//			driver.set(new FirefoxDriver(options));
			driver.set(new RemoteWebDriver(new URL(BROWSERSTACK_URL), options));
		}
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// Navigate to the base URL
		getDriver().get(loginpage_url);
		// Set the driver in the test context
		context.setAttribute("driver", getDriver());
		
	}
	
	// Helper method to get the thread-safe driver
    public static WebDriver getDriver() {
        return driver.get();
    }
		

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();	
			driver.remove();    // Clean up ThreadLocal
		}
	}
	
	@AfterSuite(alwaysRun = true)
    public void teardownSuite() {
		System.out.println("Reached after suite method.."); // Debug log
        ExtentReportManager.flushReport();
    }
	
}
