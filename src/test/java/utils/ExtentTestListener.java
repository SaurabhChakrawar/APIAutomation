package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestListener implements ITestListener {

	private static ExtentReports extent = ExtentManager.getInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName())
				.assignCategory(result.getTestClass().getName());
		test.set(extentTest);

		System.out.println("Starting Test: " + result.getMethod().getMethodName());
		test.get().info("Test Started: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("PASSED: " + result.getMethod().getMethodName());
		test.get().pass("✅ Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("FAILED: " + result.getMethod().getMethodName());
		test.get().fail("❌ Test Failed: " + result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("SKIPPED: " + result.getMethod().getMethodName());
		test.get().skip("⚠️ Test Skipped: " + result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Flushing Extent Report...");
		extent.flush();
	}

	// 🔹 Public getter so other utils (like filters) can use the current test
	public static ExtentTest getTest() {
		return test.get();
	}

	private static boolean isJson(String str) {
		if (str == null)
			return false;
		str = str.trim();
		return (str.startsWith("{") && str.endsWith("}")) || (str.startsWith("[") && str.endsWith("]"));
	}

}
