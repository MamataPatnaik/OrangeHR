	package com.automation.selenium.orangehr.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.selenium.orangehr.base.TestSetUpBase;
import com.automation.selenium.orangehr.listeners.AllureReportListener;
import com.automation.selenium.orangehr.pageobjects.SignInPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
@Listeners(AllureReportListener.class)
public class TC_OrangeHRM_Admin_02 extends TestSetUpBase{

	private WebDriver driver;
	private String expectedMsg;
	 @BeforeClass
		public void setup() {
		 driver=getDriver();

	 }
	 @Description("Verifying Login test with invalid credentials")
	 @Severity(SeverityLevel.CRITICAL)
	 @Test(description="Verifying Login test with invalid credentials")
	 public void unsuccessfulLoginTest() {
		 System.out.println("Login test with invalid credentials");
		 SignInPage obj=new SignInPage(driver);
		 expectedMsg="Invalid credentials";
		 Assert.assertTrue(obj.verifySignIn("Adminfail","failadmin1","Invalid",false,false,expectedMsg));
		 System.out.println("SignIn Failed with message: "+obj.getErrorMessage());
	 }
}
