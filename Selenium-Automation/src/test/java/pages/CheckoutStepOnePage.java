package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class CheckoutStepOnePage {
	private WebDriver driver;
	
	private By firstNameField = By.id("first-name");
	private By lastNameField = By.id("last-name");
	private By postalCodeField = By.id("postal-code");
	private By continueButton = By.id("continue");
	private By cancelButton = By.id("cancel");
	private By errorMessage = By.className("error-message-container");
	
	public CheckoutStepOnePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isDisplayed() {
		return driver.getCurrentUrl().contains("checkout-step-one.html");
	}
	
	public void enterFirstName(String firstName) {
		WaitUtils.waitForVisibility(driver, firstNameField).sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		WaitUtils.waitForClickable(driver, lastNameField).sendKeys(lastName);
	}
	
	public void enterPostalCode(String postalCode) {
		WaitUtils.waitForVisibility(driver, postalCodeField).sendKeys(postalCode);
	}
	
	public CheckoutStepTwoPage clickContinue() {
		WaitUtils.waitForClickable(driver, continueButton).click();
		return new CheckoutStepTwoPage(driver);
	}
	
	public void clickContinueExpectingError() {
		WaitUtils.waitForClickable(driver, continueButton).click();
	}
	
	public void clickCancel() {
		WaitUtils.waitForClickable(driver, cancelButton).click();
	}
	
	public String getErrorMessage() {
		return WaitUtils.waitForVisibility(driver, errorMessage).getText();
	}
	
	public CheckoutStepTwoPage fillInfoAndContinue(String firstName, String lastName, String postalCode) {
		enterFirstName(firstName);
		enterLastName(lastName);
		enterPostalCode(postalCode);
		return clickContinue();
	}
}
