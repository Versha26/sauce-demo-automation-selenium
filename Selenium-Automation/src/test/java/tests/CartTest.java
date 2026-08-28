package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.TestData;

public class CartTest extends BaseTest {
	
	private InventoryPage inventoryPage;
	private CartPage cartPage;
	
	@BeforeMethod
	public void loginAndNavigate() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);
		inventoryPage = new InventoryPage(getDriver());
	}
	
	@Test(description="After adding item in cart, cart relefts the same item")
	public void cartReflectsAddedItem() {
		inventoryPage.addItemToCart(TestData.BACKPACK);
		inventoryPage.goToCart();
		cartPage = new CartPage(getDriver());
		
		Assert.assertTrue(cartPage.isCartPageDisplayed());
		Assert.assertEquals(cartPage.getNumberOfItemsInCart(), 1);
		Assert.assertTrue(cartPage.getCartItemNames().contains(TestData.BACKPACK), null);
	}
	
	@Test(description="After adding multiple items, cart shows all the items")
	public void cartReflectsMultipleItems() {
		inventoryPage.addItemToCart(TestData.BACKPACK);
		inventoryPage.addItemToCart(TestData.JACKET);
		inventoryPage.goToCart();
		cartPage = new CartPage(getDriver());
		
		List<String> itemNames = cartPage.getCartItemNames();
		Assert.assertEquals(cartPage.getNumberOfItemsInCart(), 2);
		Assert.assertTrue(itemNames.contains(TestData.BACKPACK));
		Assert.assertTrue(itemNames.contains(TestData.JACKET));
	}
	
	@Test(description="After removing, cart doesn't show any item")
	public void removeItemFromCart() {
		inventoryPage.addItemToCart(TestData.BACKPACK);
		inventoryPage.goToCart();
		cartPage = new CartPage(getDriver());
		
		cartPage.removeItem(TestData.BACKPACK);
		Assert.assertEquals(cartPage.getNumberOfItemsInCart(), 0);
	}
	
	@Test(description="Continue Shopping option returns to Inventory page")
	public void continueShoppingReturnToInventoryPage() {
		inventoryPage.addItemToCart(TestData.BACKPACK);
		inventoryPage.goToCart();
		cartPage = new CartPage(getDriver());
		
		InventoryPage backToInventoryPage = cartPage.continueShopping();
		Assert.assertTrue(backToInventoryPage.isInventoryPageDisplayed());
	}
	
	@Test(description="Empty cart should have zero items")
	public void emptyCartHasZeroItems() {
		inventoryPage.goToCart();
		cartPage = new CartPage(getDriver());
		
		Assert.assertEquals(cartPage.getNumberOfItemsInCart(), 0);
	}
}
