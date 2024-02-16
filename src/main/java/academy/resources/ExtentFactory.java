package academy.resources;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {

	public ExtentFactory() {
		
	}
	
	private static ExtentFactory instance = new ExtentFactory();
	
	public static ExtentFactory getInstance() {
			return instance;		
	}
	
	ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();
	
	public void setExtent(ExtentTest extentTestObj) {
		extent.set(extentTestObj);
	}
	
	public ExtentTest getExtent() {
		return extent.get();
	}
	
	
}
