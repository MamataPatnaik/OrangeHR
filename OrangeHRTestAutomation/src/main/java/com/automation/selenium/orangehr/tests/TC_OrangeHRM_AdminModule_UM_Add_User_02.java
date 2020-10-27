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

public class TC_OrangeHRM_AdminModule_UM_Add_User_02 extends TestSetUpBase {

	private WebDriver driver;

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
	public void SearchResetValidation() {

		WelcomePage welcomeObj = new WelcomePage(driver);
		Admin adminObj = new Admin(driver);
		List<String> submenupath = new ArrayList<>(Arrays.asList("Admin", "User Management", "Users", "System Users"));

		Assert.assertTrue(welcomeObj.submenuClick(submenupath), "Submenu not clicked");
		Assert.assertTrue(adminObj.searchOneField("Username", "Alice.Duval"), "Search not successfull");
		Assert.assertTrue(adminObj.deleteEmployee(), "Employee not deleted");

		// UserRole

		Assert.assertTrue(welcomeObj.submenuClick(submenupath), "Submenu not clicked");
		Assert.assertTrue(adminObj.searchOneField("User Role", "ESS"), "Search not successfull");

		Assert.assertTrue(adminObj.deleteEmployee(), "Employee not deleted");

		Assert.assertTrue(adminObj.resetSearchValues());

		Assert.assertTrue(adminObj.searchTwoFields("Username", "User Role", "Alice.Duval", "ESS"),
				"Search not successfull");
		Assert.assertTrue(adminObj.deleteEmployee(), "Employee not deleted");
	}

}
