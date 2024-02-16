package academy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import academy.commonComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(id="userEmail")
	WebElement userEmailEle;
	
	@FindBy(id="userPassword")
	WebElement userPasswordEle;
	
	@FindBy(id="login")
	WebElement loginEle;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement loginErrorMsgEle;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public ProductCataloguePage loginApplication(String email, String password) {
		userEmailEle.clear();
		userEmailEle.sendKeys(email);
		userPasswordEle.clear();
		userPasswordEle.sendKeys(password);
		loginEle.click();
		return new ProductCataloguePage(driver);
	}
	
	public void goTo() {
		driver.get("https://www.rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		visibilityOfElement_Custom(loginErrorMsgEle);
		return loginErrorMsgEle.getText();
	}
	
}
