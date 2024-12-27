package baseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import extentReports.ExtentReportManager;

public class Common_functions {
	
	public static WebDriver driver = null;
	public static Properties properties = null;
	
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
	

	@BeforeTest
	public void launch_Browser() throws IOException {
		
		loadPropertyFile();
		String browser = properties.getProperty("browser");
		final String loginpage_url = properties.getProperty("test_Url");
//		final String loginpage_url2 = properties.getProperty("test_Url2");
		

		if (browser.equalsIgnoreCase("chrome")) {
			// Set up ChromeDriver dynamically using WebDriver Manager
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			// Set up GeckoDriver dynamically using WebDriver Manager
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			driver = new FirefoxDriver(options);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(loginpage_url);
		
	}
		

	@AfterTest
	public void tear_Down() {
		if (driver != null) {
			driver.quit();			
		}

	}
	
	@AfterSuite(alwaysRun = true)
    public void teardownSuite() {
		System.out.println("Reached after suite method.."); // Debug log
        ExtentReportManager.flushReport();
    }
	
}