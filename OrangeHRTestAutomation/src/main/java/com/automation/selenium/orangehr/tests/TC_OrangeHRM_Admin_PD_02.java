package com.automation.selenium.orangehr.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.selenium.orangehr.base.TestSetUpBase;
import com.automation.selenium.orangehr.pageobjects.PersonalDetails;
import com.automation.selenium.orangehr.pageobjects.SignInPage;
import com.automation.selenium.orangehr.pageobjects.WelcomePage;
import com.automation.selenium.orangehr.util.TestConstants;

public class TC_OrangeHRM_Admin_PD_02 extends TestSetUpBase{

	private WebDriver driver;
	
	//private String expectedMsg;
	
	
	  @BeforeClass
	 public void setup() {
	 
	 System.out.println("In Before Class");
	 driver=getDriver();

	 SignInPage obj=new SignInPage(driver);
	 obj.enterusername(TestConstants.VALID_USER_NAME);
	 obj.enterpassword(TestConstants.VALID_PASSWORD);
	 obj.clickOnLogin();
 
	  }
	  
	 
	 @Test
	 public void personalDetailsNameModify() {
		 WelcomePage welcomeObj=new WelcomePage(driver);
		 PersonalDetails personalDetailsObj=new PersonalDetails(driver);
		 Assert.assertTrue(welcomeObj.tabClick("MyInfo"));
		 Assert.assertTrue(personalDetailsObj.fieldModification("FirstName","Martin"));
	 }

}
