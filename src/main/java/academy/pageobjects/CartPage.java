package academy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import academy.commonComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartItemsEle;
	
	@FindBy(xpath ="(//button[@class='btn btn-primary'])[3]")
	WebElement CheckoutBtn;
	
	By cartItemsBy = By.cssSelector(".cartSection h3");
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean verifyProductMatches(String productName) {
		visibilityOfElementByLocator_Custom(cartItemsBy);
		return cartItemsEle.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
	}
	
	public CheckoutPage clickCheckoutBtn() {
		elementToBeClickable_Custom(CheckoutBtn);
		javascriptExecutorClick_Custom(CheckoutBtn);
		return new CheckoutPage(driver);
	}

}
