package academy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import academy.commonComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartItemsEle;
	
	@FindBy(css ="[placeholder='Select Country']")
	WebElement countryEle;
	
	@FindBy(xpath ="(//button[contains(@class,'ta-item')])/span[(text()=' India')]")
	WebElement indiaEle;
	
	@FindBy(css =".btnn.action__submit.ng-star-inserted")
	WebElement placeOrderBtnEle;
	
	By dropdownEle = By.cssSelector(".ta-results");
	
	By cartItemsBy = By.cssSelector(".cartSection h3");
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectCountry(String productName) {
		actionClassSendKeysWithElement_Custom(countryEle, productName);
		visibilityOfElementByLocator_Custom(dropdownEle);
		indiaEle.click();
	}
	
	public OrderSummaryPage clickPlaceOrder() {
		javascriptExecutorClick_Custom(placeOrderBtnEle);
		return new OrderSummaryPage(driver);
	}
}
