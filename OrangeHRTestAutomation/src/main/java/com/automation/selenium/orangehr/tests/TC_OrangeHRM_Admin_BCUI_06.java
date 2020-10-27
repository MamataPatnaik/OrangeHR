package com.automation.selenium.orangehr.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.selenium.orangehr.base.TestSetUpBase;

public class TC_OrangeHRM_Admin_BCUI_06 extends TestSetUpBase {

		private WebDriver driver;
		private String expectedTitle;
		private By searchBox=By.xpath("//input[@name='q']");
		private By siteLink=By.xpath("//span[contains(text(),'OrangeHRM Demo.')]");

				@BeforeClass
		public void setup() {

			System.out.println("In Before Class");
			driver = getDriver();

		}
		
		@Test
		public void searchEngineCheck() {
			expectedTitle="OrangeHRM";
			driver.navigate().to("http://www.google.com");
			driver.findElement(searchBox).sendKeys("Orange HRM Demo");
			driver.findElement(searchBox).sendKeys(Keys.ENTER);
			
			if(driver.findElement(siteLink).isDisplayed()){
				driver.findElement(siteLink).click();
				Assert.assertTrue((driver.getTitle().equals(expectedTitle)),"Required page does not open!");
				
				
			}
		}
	
}
