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

public class TC_OrangeHRM_AdminModule_Job_Employment_Status_01  extends TestSetUpBase {

	private WebDriver driver;
	
	
	  @BeforeClass
	  public void setup() {
	  
	  System.out.println("In Before Class");
	  
	  driver = getDriver(); SignInPage obj = new SignInPage(driver);
	  obj.enterusername(TestConstants.VALID_USER_NAME);
	  obj.enterpassword(TestConstants.VALID_PASSWORD); obj.clickOnLogin(); }
	 
	@Test
	public void EmploymentStatusValidation() {
		
		WelcomePage welcomeObj=new WelcomePage(driver);
		Admin adminObj=new Admin(driver);
		List<String> submenupath=new ArrayList<>(Arrays.asList("Admin","Job","Employment Status","Employment Status Heading"));
		Assert.assertTrue(welcomeObj.submenuClick(submenupath),"Submenu not clicked");
		Assert.assertTrue(adminObj.addEmployStatus("Freelance Type1"), "Emploment status not added");
		Assert.assertTrue(adminObj.selectTitle("Freelance Type1"), "Employment status not selected");
		Assert.assertTrue(adminObj.editEmpStatus("Freelance Type2"), "EmployeeStatus name not changed");
		Assert.assertTrue(adminObj.searchTitle("Freelance Type3"), "Employment status not found");
		Assert.assertTrue(adminObj.searchTitle("Freelance"), "Employment status not found");
		Assert.assertTrue(adminObj.deleteTitle(), "Delete Failed");
	}
}