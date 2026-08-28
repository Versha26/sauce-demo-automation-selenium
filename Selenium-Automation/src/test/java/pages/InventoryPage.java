package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utils.WaitUtils;

public class InventoryPage {
	private WebDriver driver;
	
	private By inventoryContainer = By.id("inventory_container");
	private By productNames = By.className("inventory_item_name");
	private By productPrices = By.className("inventory_item_price");
	private By sortDropDown = By.className("product_sort_container");
	private By cartBadge = By.className("shopping_cart_badge");
	private By cartButton = By.className("shopping_cart_link");
	
	public InventoryPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public boolean isInventoryPageDisplayed() {
		return WaitUtils.waitForVisibility(driver, inventoryContainer).isDisplayed();
	}
	
	public void addItemToCart(String productName) {
		String buttonID = "add-to-cart-"+productName.toLowerCase()
							.replace(" ", "-")
							.replace("(", "")
							.replace(")", "");
		By addButton = By.id(buttonID);
		WaitUtils.waitForClickable(driver, addButton).click();
	}
	
	public void removeItemFromCart(String productName) {
		String buttonId = "remove-"+productName.toLowerCase()
							.replace(" ", "-")
							.replace("(", "")
							.replace(")", "");
		By removeButton = By.id(buttonId);
		WaitUtils.waitForClickable(driver, removeButton).click();
	}
	
	public int getCartItemCount() {
		List<WebElement> badge = driver.findElements(cartBadge);
		if (badge.isEmpty()) return 0;
		return Integer.parseInt(badge.get(0).getText());
	}
	
	public void sortProductsBy(String visibleText) {
		WebElement dropdownElement = WaitUtils.waitForVisibility(driver, sortDropDown);
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByVisibleText(visibleText);
	}
	
	public List<String> getAllProductNames() {
		return driver.findElements(productNames).stream().map(WebElement::getText).toList();
	}
	
	public List<Double> getAllProductPrices() {
		return driver.findElements(productPrices).stream().map(e -> Double.parseDouble(e.getText().replace("$", ""))).toList();
	}
	
	public void goToCart() {
		WaitUtils.waitForClickable(driver, cartButton).click();
	}
}
