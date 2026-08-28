package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class CheckoutCompletePage {
	private WebDriver driver;
	
	private By completeHeader = By.className("complete-header");
	private By backHomeButton = By.id("back-to-products");
	
	public CheckoutCompletePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isOrderCompleted() {
		return driver.getCurrentUrl().contains("checkout-complete.html");
	}
	
	public String getConfirmationMessage() {
		return WaitUtils.waitForVisibility(driver, completeHeader).getText();
	}
	
	public InventoryPage backToProducts() {
		WaitUtils.waitForClickable(driver, backHomeButton).click();
		return new InventoryPage(driver);
	}
}
