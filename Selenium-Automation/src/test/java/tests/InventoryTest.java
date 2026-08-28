package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;
import utils.TestData;

public class InventoryTest extends BaseTest {
	private InventoryPage inventoryPage;
	
	@BeforeMethod
	public void loginFirst() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);
		inventoryPage = new InventoryPage(getDriver());
	}
	
	@Test
	public void inventoryPageLoadsAfterLogin() {
		Assert.assertTrue(inventoryPage.isInventoryPageDisplayed());
	}
	
	@Test
	public void addSingleItemToCart() {
		inventoryPage.addItemToCart(TestData.BACKPACK);
		Assert.assertEquals(inventoryPage.getCartItemCount(), 1);
	}
	
	@Test
	public void addMultipleItemsToCart() {
		inventoryPage.addItemToCart(TestData.BACKPACK);
		inventoryPage.addItemToCart(TestData.BIKE);
		Assert.assertEquals(inventoryPage.getCartItemCount(), 2);
	}
	
	@Test
	public void removeItemFromCart() {
		inventoryPage.addItemToCart(TestData.JACKET);
		inventoryPage.removeItemFromCart(TestData.JACKET);
		Assert.assertEquals(inventoryPage.getCartItemCount(), 0);
	}
	
	@Test
	public void sortProductsPriceLowToHigh() {
		inventoryPage.sortProductsBy(TestData.SORT_PRICE_LOW_HIGH);
		List<Double> prices = inventoryPage.getAllProductPrices();
		List<Double> sortedPrices = prices.stream().sorted().toList();
		Assert.assertEquals(prices, sortedPrices);
	}
	
	@Test
	public void sortProductNamesZtoA() {
		inventoryPage.sortProductsBy(TestData.SORT_NAME_Z_TO_A);
		List<String> names = inventoryPage.getAllProductNames();
		List<String> sortedNames = names.stream().sorted(java.util.Collections.reverseOrder()).toList();
		Assert.assertEquals(names, sortedNames);
	}
}
