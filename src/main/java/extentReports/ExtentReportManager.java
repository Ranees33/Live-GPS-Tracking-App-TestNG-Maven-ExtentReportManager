package extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

	public static ExtentReports extentReport;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentTest test;
	public static String testName = "";
	public static String author = "";
	public static String testCategory = "";

	// Initialize ExtentReports
	public static void setupExtentReports() {

		extentReport = new ExtentReports();
		sparkReporter = new ExtentSparkReporter("Extent-Report/VMTrackersAppReport.html");
		extentReport.attachReporter(sparkReporter);
		sparkReporter.config().setReportName("VM Trackers GPS Application Test Execution Report");
		sparkReporter.config().setTheme(Theme.DARK);

		extentReport.setSystemInfo("Environment", "QA");
		extentReport.setSystemInfo("Ranees", "Tester");

	}

	// Flush ExtentReports
	public static void flushReport() {
		if (extentReport != null) {
			extentReport.flush();
		}
	}
	

	// Create a scenario based test name
//	public static void createTest() {
//		test = extentReport.createTest(testName);
//		test.assignAuthor(author);
//		test.assignCategory(testCategory);
//	
//	}

}
