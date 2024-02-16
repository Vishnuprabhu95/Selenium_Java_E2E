package academy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import academy.commonComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderItemEle;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean verifyOrderDisplay(String productName) {
		visibilityOfElement_Custom(orderItemEle.get(0));
		return orderItemEle.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
	}
	

}
