package academy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import academy.commonComponents.AbstractComponent;

public class OrderSummaryPage extends AbstractComponent {

	WebDriver driver;
	
	@FindBy(css =".hero-primary")
	WebElement messageEle;
	
	public OrderSummaryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getOrderMessage() {
		return messageEle.getText();
	}

}
