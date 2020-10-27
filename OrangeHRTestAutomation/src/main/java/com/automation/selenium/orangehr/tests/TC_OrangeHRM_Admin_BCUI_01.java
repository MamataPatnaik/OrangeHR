package com.automation.selenium.orangehr.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.selenium.orangehr.base.TestSetUpBase;
import com.automation.selenium.orangehr.pageobjects.LandingPageValidation;
import com.automation.selenium.orangehr.pageobjects.SignInPage;
import com.automation.selenium.orangehr.pageobjects.pageLoadPerformance;
import com.automation.selenium.orangehr.util.TestConstants;

public class TC_OrangeHRM_Admin_BCUI_01 extends TestSetUpBase { 

	private WebDriver driver;
	private String[] labels = { "Assign Leave", "Leave List", "Timesheets", "Apply Leave", "My Leave", "My Timesheet" };
	private String[] hrefs = { "assignLeave", "viewLeaveList", "viewEmployeeTimesheet", "applyLeave", "viewMyLeaveList",
			"viewMyTimesheet" };
	private String[] headers = { "Quick Launch", "Employee Distribution by Subunit", "Legend",
			"Pending Leave Requests" };
	private List<String> expectedMainmenuList=new ArrayList<>(Arrays.asList("Admin","PIM","Leave","Time","Recruitment","My Info","Performance","Dashboard","Directory","Maintenance","Buzz"));
	private List<String> expectedSubmenuList=new ArrayList<>(Arrays.asList("User Management","Users","Job","Job Titles","Pay Grades","Employment Status","Job Categories","Work Shifts","Organization","General Information","Locations","Structure","Qualifications","Skills","Education","Licenses","Languages","Memberships","Nationalities","Configuration","Email Configuration","Email Subscriptions","Localization","Modules","Social Media Authentication","Register OAuth Client","Configuration","Optional Fields","Custom Fields","Data Import","Reporting Methods","Termination Reasons","Employee List","Add Employee","Reports","Apply","My Leave","Entitlements","Add Entitlements","Employee Entitlements","My Entitlements","Reports","Leave Entitlements and Usage Report","My Leave Entitlements and Usage Report","Configure","Leave Period","Leave Types","Work Week","Holidays","Leave List","Assign Leave","Candidates","Vacancies","Configure","KPIs","Trackers","Manage Reviews","Manage Reviews","My Reviews","Review List","My Trackers","Employee Trackers","Purge Records","Employee Records","Candidate Records","Access Records"));

	
	private Map<String,String> menuList=new HashMap<String,String>();
	
	
	
	
	
	@BeforeClass
	public void setup() {

		System.out.println("In Before Class");
		driver = getDriver(); 
		SignInPage obj = new SignInPage(driver);
		obj.enterusername(TestConstants.VALID_USER_NAME);
		obj.enterpassword(TestConstants.VALID_PASSWORD); 
		obj.clickOnLogin();
		  }
	
			
			  @Test public void performance() {
			  
			  pageLoadPerformance pageloadObj=new pageLoadPerformance(driver);
			  Assert.assertTrue(pageloadObj.pageLoadTime("Admin"),"Page takes more than 10 seconds to load");
			  Assert.assertTrue(pageloadObj.pageLoadTime("My Info"),"Page takes more than 10 seconds to load");
			  Assert.assertTrue(pageloadObj.pageLoadTime("Dashboard"),"Page takes more than 10 seconds to load"); }
			 

	@Test
	public void uiValidations() {
		LandingPageValidation pageObj = new LandingPageValidation(driver);
		
		
		  Assert.assertTrue(pageObj.defaultLandingPage(),"Expected Landing page not displayed");
		  Assert.assertTrue(pageObj.sectionHeaderVerify(headers),"Expected headers not present");
		  menuList=pageObj.submenuValidation(expectedMainmenuList,expectedSubmenuList);
		  if (menuList.size()==0)
		  { 
			  System.out.println("Menu Items present");
			}
		  else {
		  System.out.println("Main menu Items not found: "+(menuList.get("MainMenuItemsNotPresent")));
		  System.out.println("Submenu Items not found: "+(menuList.get("SubmenuItemsNotPresent"))); 
		  }
		  
		  
		  Assert.assertTrue(pageObj.quickLaunchLabelVerify(labels),"Expected labels not present");
		  Assert.assertTrue(pageObj.quickLaunchImageVerify(hrefs),"Expected link hrefs not present");
		 
		Assert.assertTrue(pageObj.tableValidation(),"Table total does not match");
	}

}
