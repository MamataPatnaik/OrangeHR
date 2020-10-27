package com.automation.selenium.orangehr.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.selenium.orangehr.base.TestSetUpBase;
import com.automation.selenium.orangehr.pageobjects.Admin;
import com.automation.selenium.orangehr.pageobjects.SignInPage;
import com.automation.selenium.orangehr.pageobjects.WelcomePage;
import com.automation.selenium.orangehr.util.TestConstants;

public class TC_OrangeHRM_AdminModule_Job_WorkShifts_01 extends TestSetUpBase {

	private WebDriver driver;
	
	
	  @BeforeClass
	  public void setup() {
	  
	  System.out.println("In Before Class");
	  
	  driver = getDriver(); 
	  SignInPage obj = new SignInPage(driver);
	  obj.enterusername(TestConstants.VALID_USER_NAME);
	  obj.enterpassword(TestConstants.VALID_PASSWORD); obj.clickOnLogin(); }
	 
	@Test
	public void WorkShiftsValidation() throws InterruptedException{
		
		WelcomePage welcomeObj=new WelcomePage(driver);
		Admin adminObj=new Admin(driver);
		List<String> employeeNames=new ArrayList<>(Arrays.asList("Joe Root","Sara Tencrady","Jacqueline White"));
		List<String> submenupath=new ArrayList<>(Arrays.asList("Admin","Job","Work Shifts","Work Shifts Heading"));
		Assert.assertTrue(welcomeObj.submenuClick(submenupath),"Submenu not clicked");
		Assert.assertTrue(adminObj.addWorkShift("Shift A", "08:00", "16:00", employeeNames), "WorkShift not added");
		Thread.sleep(5000);
		Assert.assertFalse(adminObj.addWorkShift("Shift A", "08:00", "16:00", employeeNames), "WorkShift not added");
		Assert.assertTrue(adminObj.searchTitle("Shift A"), "WorkShift not found");
		Assert.assertTrue(adminObj.deleteTitle(), "Delete not successfull!");
		
	}

}
