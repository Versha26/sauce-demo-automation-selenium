package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {
	public static String captureScreenshot(WebDriver driver, String testName) {
		String timestamp = new SimpleDateFormat("dd-MM-yyy_HH-mm-ss").format(new Date());
		String fileName = testName + "_" + timestamp + ".png";
		String filePath = System.getProperty("user.dir") + "/test-output/screenshots" + fileName;
		
		try {
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destination = new File(filePath);
			FileUtils.copyFile(source, destination);
			return filePath;
		} catch (IOException e) {
			throw new RuntimeException("Failed to capture screenshot: " + e.getMessage());
		}
	}
}
