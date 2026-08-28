package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.ConfigReader;
import utils.DriverFactory;

public class BaseTest {
	@BeforeMethod
	public void setup() {
		DriverFactory.initDriver(ConfigReader.get("browser"));
		DriverFactory.getDriver().get(ConfigReader.get("baseURL"));
	}
	
	public WebDriver getDriver() { 
		return DriverFactory.getDriver();
	}
	
	@AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
