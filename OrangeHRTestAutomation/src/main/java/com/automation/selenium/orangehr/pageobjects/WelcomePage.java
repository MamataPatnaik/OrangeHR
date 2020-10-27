package com.automation.selenium.orangehr.pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

import com.automation.selenium.orangehr.util.TestUtilities;

public class WelcomePage {

	private WebDriver driver;
	private By myInfoTab = By.xpath("//b[contains(text(),'My Info')]");
	private By adminTab = By.xpath("//b[contains(text(),'Admin')]");
	private By dashboardTab = By.id("menu_dashboard_index");
	private By personalDetails = By.xpath("//h1[contains(text(),'Personal Details')]");
	private By systemUsers = By.xpath("//h1[contains(text(),'System Users')]");
	private By dashboard = By.xpath("//h1[contains(text(),'Dashboard')]");
	private By subscribeBtn = By.id("Subscriber_link");

	// submenus
	private By userManagement = By.id("menu_admin_UserManagement");
	private By job = By.id("menu_admin_Job");
	private By payGrades=By.id("menu_admin_viewPayGrades");
	private By users = By.id("menu_admin_viewSystemUsers");
	private By jobTitlesheading = By.xpath("//h1[contains(text(),'Job Titles')]");
	private By payGradesheading = By.xpath("//h1[contains(text(),'Pay Grades')]");
	private By employmentStatusHeading = By.xpath("//body/div[@id='wrapper']/div[@id='content']/div[@id='customerList']/div[@id='search-results']/div[1]/h1[1]");
	private By jobTitle = By.id("menu_admin_viewJobTitleList");
	private By employmentStatus=By.id("menu_admin_employmentStatus");
	private By jobCategories=By.id("menu_admin_jobCategory");
	private By jobCategoriesHeading=By.xpath("//h1[contains(text(),'Job Categories')]");
	private By workShiftHeading=By.xpath("//h1[contains(text(),'Work Shifts')]");
	private By workShifts=By.id("menu_admin_workShift");
	private By organization=By.id("menu_admin_Organization");
	private By generalInformation=By.id("menu_admin_viewOrganizationGeneralInformation");
	private By generalInfoHeading=By.xpath("//h1[@id='genInfoHeading']");
	private boolean flag1;

	public WelcomePage(WebDriver driver) {
		this.driver = driver;

	}

	public boolean tabClick(String tabName) {
		flag1 = false;
		WebElement tab;
		switch (tabName) {

		case "MyInfo":
			tab = driver.findElement(myInfoTab);
			tab.click();
			if (driver.findElement(personalDetails).isDisplayed()) {
				flag1 = true;
				System.out.println("Navigation to Personal Details Page successfull!");
			}
			break;

		case "Admin":
			tab = driver.findElement(adminTab);
			tab.click();
			if (driver.findElement(systemUsers).isDisplayed()) {
				flag1 = true;
				System.out.println("Navigation to System Users Page successfull!");
			}
			break;

		case "Dashboard":
			tab = driver.findElement(dashboardTab);
			tab.click();
			if (driver.findElement(dashboard).isDisplayed()) {
				flag1 = true;
				System.out.println("Navigation to dashboard Page successfull!");
			}
			break;
		default:
			System.out.println("Default case");

		}

		return flag1;

	}

	public boolean welcomePageColorCheck(String colorToBeChecked) {

		WebElement subscribe = driver.findElement(subscribeBtn);
		Color color = TestUtilities.getColor(subscribe, colorToBeChecked);
		if (TestUtilities.webSafeColorCheck(color))
			flag1 = true;
		else
			flag1 = false;

		return flag1;

	}

	public boolean submenuClick(List<String> submenupath) {
		By ele = null;
		Actions action = new Actions(driver);
		List<By> submenu = new ArrayList();
		for (int i = 0; i < submenupath.size(); i++) {

			switch (submenupath.get(i)) {

			case "Admin":
				ele = adminTab;
				break;

			case "User Management":
				ele = userManagement;
				break;

			case "Users":
				ele = users;
				break;

			case "System Users":
				ele = systemUsers;
				break;

			case "Job":
				ele = job;
				break;
			case "Job Titles":
				ele = jobTitle;
				break;
			case "Job Titles Heading":
				ele = jobTitlesheading;
				break;
			case "Pay Grades":
				ele=payGrades;
				break;
			case "Pay Grades Heading":
				ele = payGradesheading;
				break;
			case "Employment Status":
			    ele=employmentStatus;
			    break;
			case "Employment Status Heading":
				ele=employmentStatusHeading;
				break;
			case "Job Categories":
				ele=jobCategories;
				break;
			case "Job Categories Heading":
			    ele=jobCategoriesHeading;
			    break;
			    
			case "Work Shifts":
				ele=workShifts;
				break;
			case "Work Shifts Heading":
				ele=workShiftHeading;
				break;
			case "Organization":
				ele=organization;
				break;
			case "General Information":
				ele=generalInformation;
				break;
			case "General Information Heading":
				ele=generalInfoHeading;
				break;
			}

			submenu.add(ele);
		}

		if (submenu.size() == 4) {

			action.moveToElement(driver.findElement(submenu.get(0))).perform();
			action.moveToElement(driver.findElement(submenu.get(1))).perform();
			action.moveToElement(driver.findElement(submenu.get(2))).click().perform();

		}

		else if (submenupath.size() == 3) {
			action.moveToElement(driver.findElement(submenu.get(0))).perform();
			action.moveToElement(driver.findElement(submenu.get(1))).click().perform();

		} else {
			driver.findElement(submenu.get(0)).click();

		}

		// to check if banner is present and required page is loaded after clicking the
		// submenu
		if (driver.findElement(submenu.get(submenu.size() - 1)).isDisplayed())
			flag1 = true;
		else
			flag1 = false;

		return flag1;

	}

}
