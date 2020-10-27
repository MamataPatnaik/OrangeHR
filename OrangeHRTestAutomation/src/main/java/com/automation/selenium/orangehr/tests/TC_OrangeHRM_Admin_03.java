package com.automation.selenium.orangehr.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.selenium.orangehr.base.TestSetUpBase;
import com.automation.selenium.orangehr.listeners.AllureReportListener;
import com.automation.selenium.orangehr.pageobjects.SignInPage;
import com.automation.selenium.orangehr.util.TestConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
@Listeners(AllureReportListener.class)
public class TC_OrangeHRM_Admin_03 extends TestSetUpBase{

	private WebDriver driver;
	private String expectedMsg;
	
	 @BeforeClass
		public void setup() {
		 driver=getDriver();

	 }
	 @Description("Verifying Login test with different credentials")
	 @Severity(SeverityLevel.CRITICAL)
	 @Test(description="Verifying Login test with different credentials")
	 public void loginMessageTest(){
		 System.out.println("Login test with different credentials");
		 SignInPage obj=new SignInPage(driver);
		//valid credential
		expectedMsg="Welcomehui";
		   Assert.assertTrue(obj.verifySignIn(TestConstants.VALID_USER_NAME,TestConstants.VALID_PASSWORD,"Valid",false,false,expectedMsg),"Login FAILED!!!");
			if(obj.logout())
				System.out.println("Logged out!");
			
		
		//invalid credentials 
		expectedMsg="Invalid credentials";
		 Assert.assertTrue(obj.verifySignIn(TestConstants.INVALID_USER_NAME,TestConstants.INVALID_PASSWORD,"Invalid",false,false,expectedMsg));
		
		
		//Correct username and blank password
		 expectedMsg="Password cannot be empty";
		Assert.assertTrue(obj.verifySignIn(TestConstants.VALID_USER_NAME,null,"Invalid",false,true,expectedMsg));
		
		
		//Wrong username correct password
		expectedMsg="Invalid credentials";
		Assert.assertTrue(obj.verifySignIn(TestConstants.INVALID_USER_NAME,TestConstants.VALID_PASSWORD,"Invalid",false,false,expectedMsg));
		
		
		//"Wrong username and Wrong password
		expectedMsg="Invalid credentials";
		Assert.assertTrue(obj.verifySignIn(TestConstants.INVALID_USER_NAME,TestConstants.INVALID_PASSWORD,"Invalid",false,false,expectedMsg));
		
		//"Wrong username and blank password
		 expectedMsg="Password cannot be empty";
			Assert.assertTrue(obj.verifySignIn(TestConstants.INVALID_USER_NAME,null,"Invalid",false,true,expectedMsg));
			
		//"Blank username and correct password
		expectedMsg="Username cannot be empty";
		Assert.assertTrue(obj.verifySignIn(null,TestConstants.VALID_PASSWORD,"Invalid",true,false,expectedMsg));
		
		//"Blank username and Wrong password
		expectedMsg="Username cannot be empty";
		Assert.assertTrue(obj.verifySignIn(null,TestConstants.INVALID_PASSWORD,"Invalid",true,false,expectedMsg));
		
		//"Blank username and Blank password
		expectedMsg="Username cannot be empty";
		Assert.assertTrue(obj.verifySignIn(null,null,"Invalid",true,true,expectedMsg));
		
		
			
	 }
}
