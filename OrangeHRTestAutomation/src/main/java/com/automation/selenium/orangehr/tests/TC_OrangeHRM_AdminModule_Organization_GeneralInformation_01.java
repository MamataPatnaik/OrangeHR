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

public class TC_OrangeHRM_AdminModule_Organization_GeneralInformation_01 extends TestSetUpBase {

	private WebDriver driver;
	
	
	  @BeforeClass
	  public void setup() {
	  
	  System.out.println("In Before Class");
	  
	  driver = getDriver(); SignInPage obj = new SignInPage(driver);
	  obj.enterusername(TestConstants.VALID_USER_NAME);
	  obj.enterpassword(TestConstants.VALID_PASSWORD); obj.clickOnLogin(); }
	 
	@Test
	public void GeneralInformationValidation(){
		WelcomePage welcomeObj=new WelcomePage(driver);
		Admin adminObj=new Admin(driver);
		List<String> fieldnames=Arrays.asList("Organization Name","Organization Mail","Organization Address","Organization City");
		List<String> fieldvalues=Arrays.asList("ORM","abc@ormail.com","Street no 234","California");
		List<String> submenupath=new ArrayList<>(Arrays.asList("Admin","Organization","General Information","General Information Heading"));
		Assert.assertTrue(welcomeObj.submenuClick(submenupath),"Submenu not clicked");
		//Assert.assertTrue(adminObj.fieldsPrePopulated(), "Fields not prepopulated");
		
		Assert.assertTrue(adminObj.editFieldsGeneralInformation(fieldnames, fieldvalues), "Edit not successfull");
		
	}


}
