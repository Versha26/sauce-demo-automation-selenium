package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.DriverFactory;
import utils.ExtentManager;
import utils.ExtentTestManager;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {
	@Override
	public void onStart(ITestContext context) {
		ExtentManager.getInstance();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
		ExtentTestManager.setTest(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
		
		String screenshotPath = ScreenshotUtils.captureScreenshot(
				DriverFactory.getDriver(), result.getMethod().getMethodName());
		ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.getTest().log(Status.SKIP, "Test skipped");
	}
	
	@Override
	public void onFinish(ITestContext context) {
		ExtentManager.getInstance().flush();;
	}
}
