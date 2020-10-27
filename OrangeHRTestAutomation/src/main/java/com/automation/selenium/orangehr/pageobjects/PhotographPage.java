package com.automation.selenium.orangehr.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PhotographPage {
	private WebDriver driver;
	private By chooseFile=By.id("photofile");
	private By uploadBtn =By.id("btnSave");
	private By actionMsg=By.xpath("//div[@class='message success fadable']");
	private By failMsg=By.xpath("//div[@class='message warning fadable']");
	private boolean flag;
	
	public PhotographPage(WebDriver driver) {
		this.driver=driver;
		
	}
	
	public boolean uploadPic(String filepath,String expectedMsg){
		
		flag=false;
		WebElement addFile =driver.findElement(chooseFile);
		addFile.sendKeys(filepath);
		//expectedMsg="Successfully Uploaded";
		driver.findElement(uploadBtn).click();
		
		 if( driver.findElement(actionMsg).isDisplayed()){
			if(driver.findElement(actionMsg).getText().contains(expectedMsg))
					flag=true;
		}
		
		else
			System.out.println("Upload unsuccessfull!");
		return flag;
	
		
		
	}
	
public boolean uploadPicFail(String filepath,String expectedMsg){
		
		flag=false;
		WebElement addFile =driver.findElement(chooseFile);
		addFile.sendKeys(filepath);
		//expectedMsg="Successfully Uploaded";
		driver.findElement(uploadBtn).click();
		 if (driver.findElement(failMsg).isDisplayed()) {
			if(driver.findElement(failMsg).getText().contains(expectedMsg))
				System.out.println(driver.findElement(failMsg).getText());
				flag=true;
			
		}
		
		
			return flag;
		
		
	}
	
}
