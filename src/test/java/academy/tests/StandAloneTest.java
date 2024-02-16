package academy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import academy.commonComponents.AbstractComponent;
import academy.pageobjects.CartPage;
import academy.pageobjects.CheckoutPage;
import academy.pageobjects.LandingPage;
import academy.pageobjects.OrderSummaryPage;
import academy.pageobjects.ProductCataloguePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
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
		
		System.out.println("Done");
		driver.close();
	}

}
