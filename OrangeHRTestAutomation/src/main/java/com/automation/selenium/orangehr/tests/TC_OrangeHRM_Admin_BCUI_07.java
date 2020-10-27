package com.automation.selenium.orangehr.tests;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.selenium.orangehr.base.TestSetUpBase;
import com.automation.selenium.orangehr.pageobjects.SignInPage;
import com.automation.selenium.orangehr.util.TestConstants;
import com.automation.selenium.orangehr.util.TestUtilities;

public class TC_OrangeHRM_Admin_BCUI_07 extends TestSetUpBase {

	private WebDriver driver;
	private By image=By.xpath("//img[@alt='OrangeHRM']");
	private By assignLeaveImage=By.xpath("//a[contains(@href,'assignLeave')]/img");
	private By leaveListImage=By.xpath("//a[contains(@href,'viewLeaveList')]/img");
	private By timesheetImage=By.xpath("//a[contains(@href,'viewEmployeeTimesheet')]/img");
	private By applyLeaveImage=By.xpath("//a[contains(@href,'applyLeave')]/img");
	private By myLeaveImage=By.xpath("//a[contains(@href,'viewMyLeaveList')]/img");
	private By myTimesheetImage=By.xpath("//a[contains(@href,'viewMyTimesheet')]/img");
	@BeforeClass
	public void setup() {

		System.out.println("In Before Class");
		driver = getDriver(); 
		SignInPage obj = new SignInPage(driver);
		obj.enterusername(TestConstants.VALID_USER_NAME);
		obj.enterpassword(TestConstants.VALID_PASSWORD); 
		obj.clickOnLogin();
		  }
	
	@Test
	public void imageLoadCheck(){
		WebElement imageWebElement=driver.findElement(assignLeaveImage);
		Assert.assertTrue(TestUtilities.imageLoadValidation(driver,driver.findElement(image)),"Image not loaded properly");
		Assert.assertTrue(TestUtilities.imageLoadValidation(driver,imageWebElement),"Image not loaded properly");
		Assert.assertTrue(TestUtilities.imageLoadValidation(driver,driver.findElement(leaveListImage)),"Image not loaded properly");
		Assert.assertTrue(TestUtilities.imageLoadValidation(driver,driver.findElement(timesheetImage)),"Image not loaded properly");
		Assert.assertTrue(TestUtilities.imageLoadValidation(driver,driver.findElement(applyLeaveImage)),"Image not loaded properly");
		Assert.assertTrue(TestUtilities.imageLoadValidation(driver,driver.findElement(myLeaveImage)),"Image not loaded properly");
	}

}
