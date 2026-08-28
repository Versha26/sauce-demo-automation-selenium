package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutCompletePage;
import pages.CheckoutStepOnePage;
import pages.CheckoutStepTwoPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.TestData;

public class CheckoutTest extends BaseTest {
	private InventoryPage inventoryPage;
	private CartPage cartPage;
	private CheckoutStepOnePage checkoutStepOnePage;
	
	@BeforeMethod
	public void loginAddItemAndGoToCheckout() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);
		
		inventoryPage = new InventoryPage(getDriver());
		inventoryPage.addItemToCart(TestData.BACKPACK);
		inventoryPage.goToCart();
		
		cartPage = new CartPage(getDriver());
		cartPage.clickCheckout();
		
		checkoutStepOnePage = new CheckoutStepOnePage(getDriver());
	}
	
	@Test(description="Test the complete checkout flow from end to end")
	public void compleCheckoutFlowEndToEnd() {
		CheckoutStepTwoPage stepTwo = checkoutStepOnePage.fillInfoAndContinue("John", "Doe", "12345");
		Assert.assertTrue(stepTwo.isDisplayed());
		
		double itemTotal = stepTwo.getItemTotal();
		double tax = stepTwo.getTax();
		double total = stepTwo.getTotal();
		Assert.assertEquals(total, itemTotal + tax, 0.01);
		
		CheckoutCompletePage completePage = stepTwo.clickFinish();
		Assert.assertTrue(completePage.isOrderCompleted());
		Assert.assertEquals(completePage.getConfirmationMessage(), TestData.CONFIRMATION_MESSAGE);
	}
	
	@Test(description="Checkout fails without first name")
	public void checkoutWithoutFirstName() {
		checkoutStepOnePage.enterLastName("Doe");
		checkoutStepOnePage.enterPostalCode("12345");
		checkoutStepOnePage.clickContinueExpectingError();
		Assert.assertTrue(checkoutStepOnePage.getErrorMessage().contains(TestData.MISSING_FIRST_NAME_ERROR));
	}
	
	@Test(description="Checkout fails without last name")
	public void checkoutWithoutLastName() {
		checkoutStepOnePage.enterFirstName("John");
		checkoutStepOnePage.enterPostalCode("12345");
		checkoutStepOnePage.clickContinueExpectingError();
		Assert.assertTrue(checkoutStepOnePage.getErrorMessage().contains(TestData.MISSING_LAST_NAME_ERROR));
	}
	
	@Test(description="Checkout fails without postal code")
	public void checkoutWithoutPostalCode() {
		checkoutStepOnePage.enterFirstName("John");
		checkoutStepOnePage.enterLastName("Doe");
		checkoutStepOnePage.clickContinueExpectingError();
		Assert.assertTrue(checkoutStepOnePage.getErrorMessage().contains(TestData.MISSING_POSTAl_ERROR));
	}
}
