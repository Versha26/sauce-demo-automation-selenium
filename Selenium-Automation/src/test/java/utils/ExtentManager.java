package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		if (extent == null) {
			String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
			String reportPath = System.getProperty("user.dir")
					+ "/test-output/ExtentReport_"+ timestamp + ".html";
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
			sparkReporter.config().setDocumentTitle("Sauce Demo Automation Report");
			sparkReporter.config().setReportName("Regresssion Test Results");
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("Browser", ConfigReader.get("browser"));
		}
		return extent;
	}
}
