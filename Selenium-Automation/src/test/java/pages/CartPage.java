package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

public class CartPage {
	private WebDriver driver;
	
	private By cartItems = By.className("cart_item");
	private By cartItemNames = By.className("inventory_item_name");
	private By cartItemPrices = By.className("inventory_item_price");
	private By continueShoppingButton = By.id("continue-shopping");
	private By checkoutButton = By.id("checkout");
	//private By cartQuantity = By.className("cart_quantity");
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isCartPageDisplayed() {
		return driver.getCurrentUrl().contains("cart.html");
	}
	
	public int getNumberOfItemsInCart() {
		return driver.findElements(cartItems).size();
	}
	
	public List<String> getCartItemNames() {
		return driver.findElements(cartItemNames).stream()
				.map(WebElement::getText)
				.toList();
	}
	
	public List<Double> getCartItemPrices() {
		return driver.findElements(cartItemPrices).stream()
				.map(e->Double.parseDouble(e.getText())).toList();
	}
	
	public void removeItem(String productName) {
		String buttonId = "remove-"+productName.toLowerCase()
							.replace(" ", "-")
							.replace("(", "")
							.replace(")", "");
		By removeButton = By.id(buttonId);
		WaitUtils.waitForClickable(driver, removeButton).click();
	}
	
	public InventoryPage continueShopping() {
		WaitUtils.waitForClickable(driver, continueShoppingButton).click();
		return new InventoryPage(driver);
	}
	
	public void clickCheckout() {
		WaitUtils.waitForClickable(driver, checkoutButton).click();
	}
}
