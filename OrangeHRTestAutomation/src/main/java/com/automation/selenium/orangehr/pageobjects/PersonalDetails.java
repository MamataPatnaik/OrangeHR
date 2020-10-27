package com.automation.selenium.orangehr.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalDetails {
    private WebDriver driver;
    private boolean flag2;
    private By empId=By.id("personal_txtEmployeeId");
    private By driverLicenseNum=By.id("personal_txtLicenNo");
    private By dateOfBirth=By.id("personal_DOB");
    private By editSaveBtn=By.id("btnSave");
    private By firstName=By.id("personal_txtEmpFirstName");
    public PersonalDetails(WebDriver driver) {
		this.driver=driver;
		
	}
    
	public boolean fieldValidation(String fieldName){
    	
    	
    	flag2=false;
    	switch(fieldName) {
    	
    	case "EmployeeId":
    		if ((!(driver.findElement(empId).isEnabled())))
    		  flag2=true;
    		  break;
    	case "Driver License":
    		if (!(driver.findElement(driverLicenseNum).isEnabled()))
    		   flag2=true;
    		   break;
    	case "Date of birth": 
    		if (!(driver.findElement(dateOfBirth).isEnabled()))
    			flag2=true;
    			break;
    	default:
    		System.out.println("Please check field name");
    		
    		}
    	
    	if (flag2==true) {
    		System.out.println(fieldName+" Field is disabled.");
    		return true;
    	}
    	else
    		return false;
    }
	
	public boolean fieldModification(String FieldName,Object newVal){
		flag2=false;
		driver.findElement(editSaveBtn).click();
		switch(FieldName) {
		
		case "FirstName":
			String enteredFirstName = (String)newVal;
			driver.findElement(firstName).clear();
			driver.findElement(firstName).sendKeys(enteredFirstName);
			driver.findElement(editSaveBtn).click();
			String testvar = driver.findElement(firstName).getAttribute("value");
			if (testvar.contains(enteredFirstName))
				flag2=true;
			
			break;
		default:
			System.out.println("Please check fieldname");
		}
		if (flag2=true) {
			System.out.println("New value set in the required field successfully");
			
		}
		
			return flag2;
		
	}
}
