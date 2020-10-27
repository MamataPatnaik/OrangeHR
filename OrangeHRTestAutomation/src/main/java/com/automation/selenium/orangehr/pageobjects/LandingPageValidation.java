package com.automation.selenium.orangehr.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class LandingPageValidation {
	private WebDriver driver;
	private boolean flag;
	private By dashboard = By.xpath("//h1[contains(text(),'Dashboard')]");
	private List<String> notFoundMainmenuList = new ArrayList();
	private List<String> notFoundsubmenuList = new ArrayList();;
	private Map<String,String> menuListItemsNotFound=new HashMap<String,String>();
	private By pendingLeaveRequestTablerows=By.xpath("//div[@id='task-list-group-panel-menu_holder']/table[@class='table hover']/tbody/tr");
	private By pendingLeaveRequestTablecols=By.xpath("//div[@id='task-list-group-panel-menu_holder']/table[@class='table hover']/tbody/tr[1]/td");
	public LandingPageValidation(WebDriver driver) {
		this.driver = driver;

	}

	public boolean defaultLandingPage() {

		if (driver.findElement(dashboard).isDisplayed())
			return true;
		else
			return false;

	}

	public boolean sectionHeaderVerify(String[] sectionHeaders) {
		for (int i = 0; i < sectionHeaders.length; i++) {

			if (driver.findElement(By.xpath("//legend[contains(text(),sectionHeaders[i])]")).isDisplayed()) {
				System.out.println(sectionHeaders[i] + " displayed");
				flag = true;
			} else
				flag = false;

		}

		return flag;

	}

	public boolean quickLaunchLabelVerify(String[] quickLaunchLabels) {

		for (int i = 0; i < quickLaunchLabels.length; i++) {

			if (driver.findElement(By.xpath("//span[contains(text(),quickLaunchLabels[i])]")).isDisplayed()) {
				System.out.println(quickLaunchLabels[i] + " displayed");
				flag = true;
			} else
				flag = false;

		}

		return flag;
	}

	public boolean quickLaunchImageVerify(String[] quickLaunchImage) {

		int j = 0;
		WebElement quickLaunch = driver.findElement(By.id("panel_resizable_0_0"));
		List<WebElement> images = quickLaunch.findElements(By.tagName("a"));
		Iterator<WebElement> i = images.iterator();

		while (i.hasNext()) {

			WebElement img = i.next();
			// System.out.println(img.getAttribute("href")+" "+quickLaunchImage[j]);
			if (img.getAttribute("href").endsWith(quickLaunchImage[j])) {

				j++;
				flag = true;
			} else
				flag = false;

		}
		return flag;
	}

	public Map<String, String> submenuValidation(List<String> expectedMainmenuList, List<String> expectedSubmenuList) {
		/*
		 * WebElement
		 * categories=driver.findElement(By.xpath("//b[contains(text(),'Admin')]"));
		 * WebElement submenu=driver.findElement(By.linkText("User Management"));
		 * WebElement submenu1=driver.findElement(By.linkText("Users")); Actions
		 * action=new Actions(driver); action.moveToElement(categories).perform();
		 * action.moveToElement(submenu).perform();
		 * action.moveToElement(submenu1).click().perform();
		 */

		//WebElement dropdowns = driver.findElement(By.xpath("//ul[@id='mainMenuFirstLevelUnorderedList']"));
		List<WebElement> dropdown1 = driver.findElements(By.xpath("//ul[@id='mainMenuFirstLevelUnorderedList']//a"));
		for (WebElement ele : dropdown1) {
			
			
			//System.out.println(ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").startsWith("<b>")) {
				// mainmenuList.add(ele.getAttribute("innerHTML"));
				String mainMenuName = (ele.getAttribute("innerHTML").replace("<b>","")).replace("</b>", "");
				if (expectedMainmenuList.contains(mainMenuName)) {
					continue;
				} else {
					notFoundMainmenuList.add(mainMenuName);
				}
			} else
			{
				String submenuName=ele.getAttribute("innerHTML");
				if (!(expectedSubmenuList.contains(submenuName)))
				{
					notFoundsubmenuList.add(submenuName);
				}
			}
		}

		if ((notFoundMainmenuList.size() > 0) || (notFoundsubmenuList.size() > 0))
		{
			menuListItemsNotFound.put("MainMenuItemsNotPresent", notFoundMainmenuList.toString());
			menuListItemsNotFound.put("SubmenuItemsNotPresent", notFoundsubmenuList.toString());
			
		}
		return menuListItemsNotFound;
		
		
		// System.out.println(ele.getText());
		/*
		 * List<WebElement> dropdown2=driver.findElements(By.xpath(
		 * "//ul[@id='mainMenuFirstLevelUnorderedList']//a[@class='arrow']"));
		 * for(WebElement ele1 : dropdown2)
		 * System.out.println(ele1.getAttribute("innerHTML"));
		 */
		// checkForMenuItems(dropdowns);

	}

	public boolean tableValidation() {
		List<WebElement> cols=driver.findElements(pendingLeaveRequestTablecols);
		System.out.println("Number of columns: "+cols.size());
		List<WebElement> rows=driver.findElements(pendingLeaveRequestTablerows);
		System.out.println("Number of rows: "+rows.size());
		for(int i=1;i <=rows.size();i++)
			System.out.println(driver.findElement(By.xpath("//div[@id='task-list-group-panel-menu_holder']//table[@class='table hover']/tbody/tr["+i+"]/td/a")).getText());
		
		  if((driver.findElement(By.xpath("//tr[@class='total']/td[2]")).getText()).contains("Total : "+rows.size()))
			  return true;
		  else
			  return false;
		  
		 
		
	}
	
	
	
	/*public void checkForMenuItems(WebElement parentELement) {
		
		 * // System.out.println("Printing the parentTageName::" + //
		 * parentELement.getTagName() ); if (parentELement.getTagName().equals("li")) {
		 * 
		 * // assert for Main menu items if
		 * (parentELement.findElements(By.tagName("a")).size() != 0) { WebElement
		 * childElement = parentELement.findElement(By.tagName("a")); WebElement
		 * parentMenuBoldTag = null;
		 * 
		 * if (childElement.findElements(By.tagName("a")).size() != 0) {
		 * parentMenuBoldTag = childElement.findElement(By.tagName("b")); // assert for
		 * the Parent menuitems System.out.println("Printing parentMenuItem::" +
		 * parentMenuBoldTag.getAttribute("innerHTML")); } // assert for sub menuitem
		 * else { // assert for the child menuitems
		 * System.out.println("Printing childMenuitem::" +
		 * childElement.getAttribute("innerHTML"));
		 * 
		 * if (parentELement.findElements(By.tagName("ul")).size() != 0) { WebElement
		 * childULElement = parentELement.findElement(By.tagName("ul"));
		 * checkForMenuItems(childULElement); }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * } // if the parent TagName is a <ul> tag else if
		 * (parentELement.getTagName().equals("ul")) { List<WebElement> childElement =
		 * parentELement.findElements(By.tagName("li")); for (WebElement liWebElement :
		 * childElement) { checkForMenuItems(liWebElement); }
		 * 
		 * }
		 * 
		 	}*/

}
