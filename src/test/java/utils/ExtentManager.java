package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/target/extent-report.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("API Automation Report");
            spark.config().setDocumentTitle("API Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // 🔹 Add basic system info
            extent.setSystemInfo("Project", "API Automation");
            extent.setSystemInfo("Tester", "Saurabh");

            // 🔹 Dynamic system info
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("OS Version", System.getProperty("os.version"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User", System.getProperty("user.name"));

            // 🔹 Add base URI (if set via system property or config)
            String baseUri = System.getProperty("baseURI", "https://reqres.in"); 
            extent.setSystemInfo("Base URI", baseUri);

            // 🔹 CI/CD info (optional)
            String buildNumber = System.getProperty("BUILD_NUMBER", "Local Run");
            String branchName = System.getProperty("BRANCH_NAME", "N/A");
            extent.setSystemInfo("Build Number", buildNumber);
            extent.setSystemInfo("Branch", branchName);
        }
        return extent;
    }
}
