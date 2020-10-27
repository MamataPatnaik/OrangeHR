 package com.automation.selenium.orangehr.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestSetUpBase {
	
	private WebDriver driver;
	static String driverPath="C:\\Users\\neebal\\chromedriver_win32\\";
	
	public WebDriver getDriver() {
	  return driver;
	}
	
	private void setDriver(String browserType,String appUrl) {
		switch(browserType) {
		   
		case "chrome": 
			driver=initChromeDriver(appUrl);
			break;
		
		case "firefox": 
			driver=initFirefoxDriver(appUrl);
			break;
		default:
			System.out.println("Browser: "+browserType+"is invalid .Launching firefox");
			driver=initFirefoxDriver(appUrl);
		
		}
		}

	
	private static WebDriver initChromeDriver(String appUrl) {
		// TODO Auto-generated method stub
		System.out.println("Launcing google chrome browser");
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appUrl);
		
		return driver;
	}
	
	
	private static WebDriver initFirefoxDriver(String appUrl) {
		// TODO Auto-generated method stub
		System.out.println("Launching firefox browser");
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appUrl);
		return driver;
		
	}

	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String appURL) {
		
		System.out.println("Print browsertype::" + browserType);
		
		System.out.println("Print appURL::" + appURL);
		
		try {
			setDriver(browserType, appURL);

		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

   @AfterClass
   public void teardown() {
	//driver.quit();
 }
}
	
	
	


