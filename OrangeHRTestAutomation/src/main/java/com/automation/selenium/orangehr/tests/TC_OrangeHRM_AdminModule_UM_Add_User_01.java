package com.automation.selenium.orangehr.tests;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.selenium.orangehr.base.TestSetUpBase;
import com.automation.selenium.orangehr.pageobjects.Admin;
import com.automation.selenium.orangehr.pageobjects.SignInPage;
import com.automation.selenium.orangehr.pageobjects.WelcomePage;
import com.automation.selenium.orangehr.util.TestConstants;

public class TC_OrangeHRM_AdminModule_UM_Add_User_01 extends TestSetUpBase {

	private WebDriver driver;
	Logger logger=Logger.getLogger(TC_OrangeHRM_AdminModule_UM_Add_User_01.class);
	@BeforeClass
	public void setup() {

		System.out.println("In Before Class");
		logger.debug("Before class");
		driver = getDriver(); 
		SignInPage obj = new SignInPage(driver);
		obj.enterusername(TestConstants.VALID_USER_NAME);
		obj.enterpassword(TestConstants.VALID_PASSWORD); 
		obj.clickOnLogin();
		  }
	
	@Test
	public void AdminModuleValidation() {
		List<String> submenupath=new ArrayList<>(Arrays.asList("Admin","User Management","Users","System Users"));
		
		/*
		 * StringBuilder pipeSeperatedMenuBuilder = new StringBuilder();
		 * 
		 * for(int index = 0; index < submenupath.size() ; index++) {
		 * 
		 * pipeSeperatedMenuBuilder.append(submenupath.get(index));
		 * 
		 * if(index <= submenupath.size() -1) { pipeSeperatedMenuBuilder.append("|"); }
		 * 
		 * System.out.println("Print pipeSeperatedMenuBuilder::" +
		 * pipeSeperatedMenuBuilder.toString());
		 * 
		 * 
		 * }
		 */			
				
		WelcomePage welcomeObj=new WelcomePage(driver);
		Admin adminObj=new Admin(driver);
		
		 Assert.assertTrue(welcomeObj.submenuClick(submenupath),"Submenu not clicked");
		 Assert.assertTrue(adminObj.addExistingUser("Fiona Grace", "Grace16","AdminUser@123pwd",null), "Add User failed");
		  System.out.println("Add user Passed");
		  
		  //Error messages 
		  Assert.assertFalse(adminObj.addExistingUser("Fiona2 Grace","Grace3", "AdminUser@123pwd","Employee does not exist"), "Add User failed");
		  System.out.println("Employee does not exist displayed");
		  
		  Assert.assertFalse(adminObj.addExistingUser("Fiona Grace","ab","AdminUser@123pwd","Should have at least 5 characters"), "Add User failed");
		 
		Assert.assertTrue(adminObj.sortColumn(),"Column not sorted");
		logger.debug("Column  sorted");
		
	}


}
