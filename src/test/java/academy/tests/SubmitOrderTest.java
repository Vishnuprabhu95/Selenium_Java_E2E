package academy.tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import academy.pageobjects.CartPage;
import academy.pageobjects.CheckoutPage;
import academy.pageobjects.LandingPage;
import academy.pageobjects.OrderPage;
import academy.pageobjects.OrderSummaryPage;
import academy.pageobjects.ProductCataloguePage;
import academy.testComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{

	@Test
	public void submitOrder(ITestContext context) throws IOException {
		String productName = "ZARA COAT 3";
		ProductCataloguePage productCataloguePage = landingPage.loginApplication("ankitn@yopmail.com", "Passcode@95");		
		productCataloguePage.addProductToCart(productName);		
		CartPage cartPage = productCataloguePage.goToCart();				
		Boolean match = cartPage.verifyProductMatches(productName);
		Assert.assertTrue(match);	
		CheckoutPage checkoutPage = cartPage.clickCheckoutBtn();
		checkoutPage.selectCountry("India");
		OrderSummaryPage orderSummaryPage = checkoutPage.clickPlaceOrder();	
		String message = orderSummaryPage.getOrderMessage();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
		context.setAttribute("productName", productName);
		logOut();
	}
	
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest(ITestContext context) {
		String productName = (String) context.getAttribute("productName");
		ProductCataloguePage productCataloguePage = landingPage.loginApplication("ankitn@yopmail.com", "Passcode@95");
		OrderPage orderPage = productCataloguePage.goToOrder();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName+"s"));
		logOut();
	}
}
