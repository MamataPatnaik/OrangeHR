package com.automation.selenium.orangehr.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.selenium.orangehr.base.TestSetUpBase;
import com.automation.selenium.orangehr.pageobjects.MyInfo;
import com.automation.selenium.orangehr.pageobjects.PhotographPage;
import com.automation.selenium.orangehr.pageobjects.SignInPage;
import com.automation.selenium.orangehr.pageobjects.WelcomePage;
import com.automation.selenium.orangehr.util.TestConstants;

public class TC_OrangeHRM_Admin_PIC_01 extends TestSetUpBase{

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
	 public void uploadPicJPG() {
	 WelcomePage welcomeObj=new WelcomePage(driver);
	 PhotographPage photoPageObj=new PhotographPage(driver);
	 MyInfo myinfoObj=new MyInfo(driver);
	 Assert.assertTrue(welcomeObj.tabClick("MyInfo"));
	 Assert.assertTrue(myinfoObj.clickonChangePic(),"Change Pic link not cicked");
	 Assert.assertTrue(photoPageObj.uploadPic("C:\\Users\\neebal\\selenium.jpg","Successfully Uploaded"),"Photo not uploaded successfully");
	 
	 }
}
