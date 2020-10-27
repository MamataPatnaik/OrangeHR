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

public class TC_OrangeHRM_AdminModule_Job_JobCategories_01 extends TestSetUpBase {

	private WebDriver driver;
	
	
	  @BeforeClass
	  public void setup() {
	  
	  System.out.println("In Before Class");
	  
	  driver = getDriver(); SignInPage obj = new SignInPage(driver);
	  obj.enterusername(TestConstants.VALID_USER_NAME);
	  obj.enterpassword(TestConstants.VALID_PASSWORD); obj.clickOnLogin(); }
	 
	@Test
	public void JobCategoriesValidation(){
		WelcomePage welcomeObj=new WelcomePage(driver);
		Admin adminObj=new Admin(driver);
		List<String> submenupath=new ArrayList<>(Arrays.asList("Admin","Job","Job Categories","Job Categories Heading"));
		Assert.assertTrue(welcomeObj.submenuClick(submenupath),"Submenu not clicked");
		Assert.assertTrue(adminObj.addjobCategories("Officer"), "Job category not added");
		Assert.assertTrue(adminObj.selectTitle("Officer"), "Job Category not selected");
		Assert.assertTrue(adminObj.editJobCategory("Officer Type2"), "Job Category name not changed");
		Assert.assertTrue(adminObj.searchTitle("Officer Type2"), "Job Category not found");
		Assert.assertTrue(adminObj.searchTitle("Craft Workers"), "Job Category not found");
		Assert.assertTrue(adminObj.deleteTitle(), "Delete Failed");
	}


}
