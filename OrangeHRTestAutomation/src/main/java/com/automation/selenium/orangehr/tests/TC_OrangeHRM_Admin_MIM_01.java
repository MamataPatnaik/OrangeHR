package com.automation.selenium.orangehr.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.selenium.orangehr.base.TestSetUpBase;
import com.automation.selenium.orangehr.pageobjects.PersonalDetails;
import com.automation.selenium.orangehr.pageobjects.SignInPage;
import com.automation.selenium.orangehr.pageobjects.WelcomePage;
import com.automation.selenium.orangehr.util.TestConstants;

public class TC_OrangeHRM_Admin_MIM_01 extends TestSetUpBase{

	private WebDriver driver;
	private String expectedMsg;
	
	 @BeforeClass
		public void setup() {
		 driver=getDriver();

	 }
	 @Test
	 public void personalDetailsfieldVerification() {
		 
		 SignInPage obj=new SignInPage(driver);
		 WelcomePage welcomeObj=new WelcomePage(driver);
		 PersonalDetails personalDetailsObj=new PersonalDetails(driver);
		 
			//valid credential
			expectedMsg="Welcome";
			Assert.assertTrue(obj.verifySignIn(TestConstants.VALID_USER_NAME,TestConstants.VALID_PASSWORD,"Valid",false,false,expectedMsg));
			Assert.assertTrue(welcomeObj.tabClick("MyInfo"));
			Assert.assertTrue(personalDetailsObj.fieldValidation("EmployeeId"));
			Assert.assertTrue(personalDetailsObj.fieldValidation("Driver License"));
			Assert.assertTrue(personalDetailsObj.fieldValidation("Date of birth"));
			
	 }

}
