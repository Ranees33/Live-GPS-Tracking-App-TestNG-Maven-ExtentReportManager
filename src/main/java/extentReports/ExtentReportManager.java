package extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

	public static ExtentReports extentReport;
	public static ExtentSparkReporter sparkReporter;
//	public static ExtentTest test;
//	public static String testName = "";
//	public static String author = "";
//	public static String testCategory = "";
	
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();  // ThreadLocal for parallel test execution

	// Initialize ExtentReports
	public static void setupExtentReports() {
		
		System.out.println("Initializing ExtentReports...");  // Debug log

		extentReport = new ExtentReports();
		sparkReporter = new ExtentSparkReporter("Extent-Report/VMTrackersAppReport.html");
		extentReport.attachReporter(sparkReporter);
		sparkReporter.config().setReportName("VM Trackers GPS Application Test Execution Report");
		sparkReporter.config().setTheme(Theme.DARK);

		extentReport.setSystemInfo("Environment", "QA");
		extentReport.setSystemInfo("Ranees", "Tester");
		
		System.out.println("ExtentReports initialized successfully."); // Debug log

	}

	// Flush ExtentReports
	public static void flushReport() {
		if (extentReport != null) {
			extentReport.flush();
		}
	}

	// Create a new test entry in the report
    public static void startTest(String testName, String author, String category) {
        // Set the test information
    	test.set(extentReport.createTest(testName));
        test.get().assignAuthor(author);
        test.get().assignCategory(category);
        
    }
    
    // Get the current test instance
    public static ExtentTest getTest() {
        return test.get();
    }

}
