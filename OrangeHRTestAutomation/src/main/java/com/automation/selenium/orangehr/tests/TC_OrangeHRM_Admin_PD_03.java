package com.automation.selenium.orangehr.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.selenium.orangehr.base.TestSetUpBase;
import com.automation.selenium.orangehr.pageobjects.ContactDetails;
import com.automation.selenium.orangehr.pageobjects.MyInfo;
import com.automation.selenium.orangehr.pageobjects.PersonalDetails;
import com.automation.selenium.orangehr.pageobjects.SignInPage;
import com.automation.selenium.orangehr.pageobjects.WelcomePage;
import com.automation.selenium.orangehr.util.TestConstants;

public class TC_OrangeHRM_Admin_PD_03 extends TestSetUpBase{

	private WebDriver driver;
	
	
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
	 //negative values for mobile number in contact details
	 public void contactDetailNumberModify() {
		 MyInfo myinfoObj=new MyInfo(driver);
		 ContactDetails contactDetailsObj=new ContactDetails(driver);
		 WelcomePage welcomeObj=new WelcomePage(driver);
		// PersonalDetails personalDetailsObj=new PersonalDetails(driver);
		 Assert.assertTrue(welcomeObj.tabClick("MyInfo"));
		 Assert.assertTrue(myinfoObj.clickonlinks("Contact Details"));
		 Assert.assertTrue(contactDetailsObj.setNewValue("Mobile Number", "abc"));
	 }
	

}
