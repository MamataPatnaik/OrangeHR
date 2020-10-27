package com.automation.selenium.orangehr.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyInfo {
private WebDriver driver;
private boolean flag;
private By contactDetails=By.linkText("Contact Details");
private By contactDetailsPage=By.xpath("//h1[contains(text(),'Contact Details')]");
private By changePic=By.id("empPic");
private By photograph=By.xpath("//h1[contains(text(),'Photograph')]");
public MyInfo(WebDriver driver) {
	this.driver=driver;
	
}

public boolean clickonlinks(String linktobeClicked) {
	flag=false;
	switch(linktobeClicked) {
	
	case "Contact Details":
		driver.findElement(contactDetails).click();
		if(driver.findElement(contactDetailsPage).isDisplayed())
			flag=true;
		break;
		
	default:
		System.out.println("Please check the link to be clicked");
		
	}
	if (flag==true) {
		System.out.println("Link clicked successfully");
		
	}
	
		return flag;
}

public boolean clickonChangePic() {
	driver.findElement(changePic).click();
	if(driver.findElement(photograph).isDisplayed())
		return true;
	else
		return false;
	
}
}
