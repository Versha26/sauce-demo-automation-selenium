package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {
	@Test(dataProvider = "validLoginData")
	public void testValidLogin(String username, String password) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(username, password);
		Assert.assertTrue(getDriver().getCurrentUrl().contains("inventory.html"));
	}
	
	@Test
	public void testLockedOutUser() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login("locked_out_user", "secret_sauce");
		Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"));
	}
	
	@Test
	public void testInvalidPassword() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login("standard_user", "wrong_password");
		Assert.assertTrue(loginPage.getErrorMessage().contains("do not match"));
	}
	
	@DataProvider(name = "validLoginData")
	public Object[][] getData() {
		return new Object[][] {
			{"standard_user", "secret_sauce"},
			{"performance_glitch_user", "secret_sauce"}
		};
	}
}
