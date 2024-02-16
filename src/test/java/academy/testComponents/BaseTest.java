package academy.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;
import com.beust.jcommander.Parameter;

import academy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	ThreadLocal<WebDriver> driverThread = new ThreadLocal<WebDriver>();
	
	public WebDriver initializeDriver(String brow) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/academy/resources/Global.properties");
		prop.load(fip);
		
//		String browserName = prop.getProperty("browser");
		String browserName = brow;
			
		DesiredCapabilities cap = new DesiredCapabilities();
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("chromeG")) {
			cap.setBrowserName("chrome");
			driver = new RemoteWebDriver(new URL("http://localhost:4444/"),cap);
		}else if(browserName.equalsIgnoreCase("firefoxG")) {
			cap.setBrowserName("firefox");
			driver = new RemoteWebDriver(new URL("http://localhost:4444/"),cap);
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
	}
	
	private void setDriver(WebDriver driverLocal) {
		driverThread.set(driverLocal);
	}
	
	private WebDriver getDriver() {
		return driverThread.get();
	}
	
	@Parameters({"browserName"})
	@BeforeTest
	public LandingPage launchApplication(String browser) throws IOException {
		driver = initializeDriver(browser);
		setDriver(driver);
		driver = getDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	public void logOut() {
		driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[4]")).click();
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
	
}
