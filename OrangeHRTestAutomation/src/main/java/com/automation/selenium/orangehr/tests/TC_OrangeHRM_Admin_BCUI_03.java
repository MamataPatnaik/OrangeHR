package com.automation.selenium.orangehr.tests;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.selenium.orangehr.base.TestSetUpBase;
import com.automation.selenium.orangehr.pageobjects.SignInPage;
import com.automation.selenium.orangehr.pageobjects.WelcomePage;
import com.automation.selenium.orangehr.util.TestConstants;

public class TC_OrangeHRM_Admin_BCUI_03 extends TestSetUpBase {

	private WebDriver driver;

	@BeforeClass
	public void setup() {

		System.out.println("In Before Class");
		driver = getDriver();

	}

	@Test
	public void colorValidation() {

		SignInPage obj = new SignInPage(driver);
		WelcomePage welcomePageObj = new WelcomePage(driver);
		Assert.assertTrue(obj.signInpageColorCheck("background-color"),"Color is not in color pallette");

		obj.enterusername(TestConstants.VALID_USER_NAME);
		obj.enterpassword(TestConstants.VALID_PASSWORD);
		obj.clickOnLogin();
		Assert.assertTrue(welcomePageObj.welcomePageColorCheck("color"),"Color is not a web safe color");
		

	}

}
