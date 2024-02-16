package academy.commonComponents;

import java.time.Duration;

import org.apache.commons.lang3.event.EventListenerSupport;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import academy.pageobjects.CartPage;
import academy.pageobjects.OrderPage;
import academy.resources.ExtentFactory;

public class AbstractComponent {

	WebDriver driver;

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartEle;

	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderEle;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public CartPage goToCart() {
		cartEle.click();
		return new CartPage(driver);
	}

	public OrderPage goToOrder() {
		orderEle.click();
		return new OrderPage(driver);
	}

	public void visibilityOfElementByLocator_Custom(By findBy) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "visibilityOfElementByLocator_Custom Passed");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "visibilityOfElementByLocator_Custom Failed");
		}
	}

	public void visibilityOfElement_Custom(WebElement ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(ele));
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "visibilityOfElement_Custom Passed");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "visibilityOfElement_Custom Failed");
		}
	}

	public void invisibilityOfElementByLocator_Custom(By findBy) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "invisibilityOfElementByLocator_Custom Passed");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "invisibilityOfElementByLocator_Custom Failed");
		}
	}

	public void elementToBeClickable_Custom(WebElement ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "elementToBeClickable_Custom Passed");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "elementToBeClickable_Custom Failed");
		}
	}

	public void javascriptExecutorClick_Custom(WebElement ele) {
		try {
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].click();", ele);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "javascriptExecutorClick_Custom Passed");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "javascriptExecutorClick_Custom Failed");
		}
	}

	public void actionClassSendKeysWithElement_Custom(WebElement ele, String text) {
		try {
			Actions a = new Actions(driver);
			a.sendKeys(ele, text).build().perform();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "actionClassSendKeysWithElement_Custom Passed");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "actionClassSendKeysWithElement_Custom Failed");
		}
	}

}
