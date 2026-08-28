package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

public class CheckoutStepTwoPage {
	private WebDriver driver;
	
	private By itemNames = By.className("inventory_item_name");
	private By itemTotalLabel = By.className("summary_subtotal_label");
	private By taxLabel = By.className("summary_tax_label");
	private By totalLabel = By.className("summary_total_label");
	private By finishButton = By.id("finish");
	private By cancelButton = By.id("cancel");
	
	public CheckoutStepTwoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isDisplayed() {
		return driver.getCurrentUrl().contains("checkout-step-two.html");
	}
	
	public List<String> getItemNames() {
		return driver.findElements(itemNames).stream()
				.map(WebElement::getText).toList();
	}
	
	public double getItemTotal() {
		String text = WaitUtils.waitForVisibility(driver, itemTotalLabel).getText();
		return Double.parseDouble(text.replace("Item total: $", ""));
	}
	
	public double getTax() {
		String text = WaitUtils.waitForVisibility(driver, taxLabel).getText();
		return Double.parseDouble(text.replace("Tax: $",""));
	}
	
	public double getTotal() {
		String text = WaitUtils.waitForVisibility(driver, totalLabel).getText();
		return Double.parseDouble(text.replace("Total: $", ""));
	}
	
	public void clickCancel() {
		WaitUtils.waitForClickable(driver, cancelButton).click();
	}
	
	public CheckoutCompletePage clickFinish() {
		WaitUtils.waitForClickable(driver, finishButton).click();
		return new CheckoutCompletePage(driver);
	}
}
