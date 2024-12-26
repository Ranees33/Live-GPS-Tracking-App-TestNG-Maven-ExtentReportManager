package testCases;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int retryCount = 0;                     // Current retry attempt
	private static final int maxRetryCount = 3;     // Maximum retry attempts

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount && shouldRetry(result)) {
			retryCount++;
			System.out.println("Retrying test: " + result.getName() + " | Attempt: " + (retryCount + 1));
			return true;      // Retry the test

		}

		System.out.println("Test" + "'" + result.getName() + "'" + "failed after " + maxRetryCount + " attempts: ");
		return false;         // Stop retrying after maxRetryCount
	}
	

	/**
	 * Determine whether the test should be retried based on the exception cause.
	 */
	

	private boolean shouldRetry(ITestResult result) {
		Throwable cause = result.getThrowable();
		if (cause != null) {
			// Retry only for specific transient issues
			return cause instanceof org.openqa.selenium.TimeoutException
					|| cause instanceof org.openqa.selenium.NoSuchElementException
					|| cause instanceof org.openqa.selenium.StaleElementReferenceException;
		}
		
		return false;
	}

}
