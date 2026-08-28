package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	public static void initDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
		} else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver();
			driver.set(new FirefoxDriver());
		}
		driver.get().manage().window().maximize();
	}
	
	public static void quitDriver() {
		if(driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
