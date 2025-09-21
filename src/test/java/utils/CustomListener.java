package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting Test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Failed: " + result.getName());
        // Optionally log request/response here
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== Suite Started: " + context.getName() + " ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=== Suite Finished: " + context.getName() + " ===");
    }
}
