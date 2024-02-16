package academy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import academy.commonComponents.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent{
	
	WebDriver driver;
	
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	By productsBy = By.cssSelector(".mb-3");
	
	By addToCartBy = By.cssSelector(".card-body button:last-of-type");
	
	By toastBy = By.cssSelector("#toast-container");
	
	By spinnerBy = By.cssSelector(".ng-animating");
	
	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public List<WebElement> getProductList() {
		visibilityOfElementByLocator_Custom(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		return getProductList().stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	}
	
	public void addProductToCart(String productName) {
		getProductByName(productName).findElement(addToCartBy).click();
		visibilityOfElementByLocator_Custom(toastBy);
		invisibilityOfElementByLocator_Custom(spinnerBy);
	}
	
}
