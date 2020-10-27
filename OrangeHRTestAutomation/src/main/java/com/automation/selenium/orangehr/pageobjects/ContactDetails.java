package com.automation.selenium.orangehr.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactDetails {
	 private WebDriver driver;
	 private By mobileNumber=By.id("contact_emp_mobile");
	 private By editSaveBtn=By.id("btnSave");
	 private By errorMsg=By.xpath("//span[contains(text(),'Allows numbers and only + - / ( )')]");
	 private boolean flag;
	 public ContactDetails(WebDriver driver) {
			this.driver=driver;
			
		}
	 public boolean setNewValue(String fieldName,String newValue) {
		 flag=false;
		 driver.findElement(editSaveBtn).click();
		 switch (fieldName) {
		 case "Mobile Number":
			 driver.findElement(mobileNumber).sendKeys(newValue);
			 if(driver.findElement(errorMsg).isDisplayed())
				 flag=true;
			 break;
			
		default:
			System.out.println("Please check the field to be modified");
			
			 
		 }
		 if (flag==true) {
			 System.out.println("Message verified successfully: "+driver.findElement(errorMsg).getText());
			 
		 }
		 
			 return flag;
	 }
	    
}
