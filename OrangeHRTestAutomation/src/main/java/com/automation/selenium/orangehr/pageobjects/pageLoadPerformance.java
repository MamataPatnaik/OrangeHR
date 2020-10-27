package com.automation.selenium.orangehr.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class pageLoadPerformance {
	
	private WebDriver driver;
	
	
	public pageLoadPerformance(WebDriver driver) {
		this.driver=driver;
		
	}
	
	public boolean pageLoadTime(String Page) {
		WelcomePage welcomePageObj=new WelcomePage(driver);
		long start = System.currentTimeMillis();
		switch(Page) {
		
		case "My Info": 
			
			  welcomePageObj.tabClick("MyInfo");
			  break;
			  
		case "Admin":
			  welcomePageObj.tabClick("Admin");
			  break;
			  
		case "Dashboard":
			  welcomePageObj.tabClick("Dashboard");
			  break;
			  
			
		default:
			System.out.println("Please check the link to be clicked");
			  
		}
			
		long finish = System.currentTimeMillis();
		long totalTime = finish - start; 
			  System.out.println("Total Time for page load - "+totalTime); 
			  System.out.println("Total time for page load in seconds: "+(totalTime/1000));
			if ((totalTime/1000)<10)
				return true;
			else
				return false;
		}
		
	}



