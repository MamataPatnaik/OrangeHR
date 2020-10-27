package com.automation.selenium.orangehr.tests;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.selenium.orangehr.base.TestSetUpBase;
import com.automation.selenium.orangehr.pageobjects.SignInPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import com.automation.selenium.orangehr.listeners.*;

@Listeners(AllureReportListener.class)
public class TC_OrangeHRM_Admin_01 extends TestSetUpBase{
	private WebDriver driver;
	
 @BeforeClass
	public void setup() {
	 driver=getDriver();

 }

 @Description("Verifying Login ,Title and Logo")
 @Severity(SeverityLevel.CRITICAL)
 @Test(description="Verifying Login,Title and Logo of the page.")
 public void loginTest(){
	 
	 System.out.println("Login test with valid credentials");
	SignInPage obj=new SignInPage(driver);
	Assert.assertTrue(obj.verifySignInPageTitle());
	System.out.println("Title of the page verified");
	Assert.assertTrue(obj.verifyLogoDisplayed());
	System.out.println("Logo of the page verified");
	Assert.assertTrue(obj.verifySignIn("Admin","admin123","Valid",false,false,"Welcome"));
	System.out.println("Sign In successfull with valid credentials");
 }
 
}
