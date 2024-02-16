package academy.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import academy.pageobjects.CartPage;
import academy.pageobjects.CheckoutPage;
import academy.pageobjects.OrderSummaryPage;
import academy.pageobjects.ProductCataloguePage;
import academy.testComponents.BaseTest;

public class ErrorValidationTest extends BaseTest{

	@Test
	public void IncorrectCredentialValidation() throws IOException {
		ProductCataloguePage productCataloguePage = landingPage.loginApplication("ankitn@yopmail.com", "Passcodes@95");		
		String message = landingPage.getErrorMessage();
		Assert.assertTrue(message.equals("Incorrect email ors password."));	
		logOut();
	}
	
	@Test
	public void productNameErrorValidation() throws IOException {
		String productName = "ZARA COAT 3";
		ProductCataloguePage productCataloguePage = landingPage.loginApplication("rajmari67@yopmail.com", "PasscodeRaj@95");		
		productCataloguePage.addProductToCart(productName);		
		CartPage cartPage = productCataloguePage.goToCart();				
		Boolean match = cartPage.verifyProductMatches(productName+"3");
		Assert.assertFalse(match);	
		logOut();
	}
	
}
