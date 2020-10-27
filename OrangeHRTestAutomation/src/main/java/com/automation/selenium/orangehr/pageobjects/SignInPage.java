package com.automation.selenium.orangehr.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.selenium.orangehr.util.TestUtilities;

import io.qameta.allure.Step;

public  class SignInPage  {

	private WebDriver driver;
	private By loginButton=By.id("btnLogin");
	private By username=By.id("txtUsername");
	private By password=By.id("txtPassword");
	private By welcomeMsg=By.id("welcome");
	private boolean flag;
	private By err=By.xpath("//span[@id='spanMessage']");
	private By logoutLink=By.xpath("//a[contains(text(),'Logout')]");
	public SignInPage(WebDriver driver) {
	this.driver=driver;
	
	}
	
	public String getSignInPageTitle() {
		String signInPagetitle=driver.getTitle();
		return signInPagetitle;
	}

	public boolean verifySignInPageTitle() {
		String expectedTitle="OrangeHRM";
		return getSignInPageTitle().contains(expectedTitle);
	}

	public boolean verifyLogoDisplayed() {
		return driver.findElement(By.xpath("//body/div[@id='wrapper']/div[@id='content']/div[@id='divLogin']/div[@id='divLogo']/img[1]")).isDisplayed();
	}
	/*
	 * public boolean verifySignIn(String uname,String passwd1,String type) {
		enterusername(uname);
		enterpassword(passwd1);
		clickOnLogin();
	 * if(driver.findElement(welcomeMsg).isDisplayed())
	 * return true;
	 * else
	 * return false;
	 * }
	 */
	@Step("Login Step with username {0},password {1}")
	public boolean verifySignIn(String uname,String passwd1,String type, boolean isUserNameEmpty,boolean isPasswordEmpty,String msgExpected) {
		flag=false;
		if(!isUserNameEmpty)
		{
			enterusername(uname);
			
		}
		
		if(!isPasswordEmpty)
		{
			enterpassword(passwd1);
		}
		
		clickOnLogin();
		
		switch(type) {
		case "Valid" :
			if(driver.findElement(welcomeMsg).isDisplayed()) {
			
				if(driver.findElement(welcomeMsg).getText().equals(msgExpected)) {
					System.out.println("Sign In successfull with valid credentials");
					flag=true;
					break;
				}
				else {
					return false;
				}
			}
			
		case "Invalid" :
			if(driver.findElement(err).isDisplayed()){
			
				if(driver.findElement(err).getText().equals(msgExpected)) {
					System.out.println("SignIn Failed with message: "+getErrorMessage());
					WebElement usrname=driver.findElement(username);
					WebElement passwd=driver.findElement(password);
					usrname.clear();
					passwd.clear();
					flag=true;
					break;
				}
				else {
					WebElement usrname=driver.findElement(username);
					WebElement passwd=driver.findElement(password);
					usrname.clear();
					passwd.clear();
					return false;
				}
			}
			
			
		default:
			flag=false;
		}
 		
			return flag;
		}
		
			
	

	public String getErrorMessage() {
		// TODO Auto-generated method stub
		String msg=null;
		WebElement errmsg=driver.findElement(err);
		if(errmsg.isDisplayed())
		msg=errmsg.getText();
		return msg;
	}

	public void clickOnLogin() {
		// TODO Auto-generated method stub
		WebElement loginBtn=driver.findElement(loginButton);
		if(loginBtn.isDisplayed())
			loginBtn.click();
	}

	public void enterpassword(String pwd) {
		// TODO Auto-generated method stub
		WebElement passwd=driver.findElement(password);
		if(passwd.isDisplayed())
			passwd.sendKeys(pwd);
	}

	public void enterusername(String userName) {
		// TODO Auto-generated method stub
		WebElement usrname=driver.findElement(username);
		if (usrname.isDisplayed()) {
			if(usrname.getText()!="")
				usrname.clear();
			usrname.sendKeys(userName);
		}
	} 
	
	public boolean logout(){
	//	WebDriverWait wait=new WebDriverWait(driver,10);
	if (driver.findElement(welcomeMsg).isDisplayed()) {
		driver.findElement(welcomeMsg).click();
		
		if (driver.findElement(logoutLink).isEnabled()) {
			System.out.println("logout displayed");
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//WebElement element=wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
			//element.click();
			driver.findElement(logoutLink).click();
			System.out.println("Logout");
		}
	}
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	return (driver.findElement(loginButton).isDisplayed());
	
	}
	public boolean signInpageColorCheck(String colorToBeChecked) {
			
			Color color=TestUtilities.getColor(driver.findElement(loginButton),colorToBeChecked);
			if (TestUtilities.webSafeColorCheck(color))
			   flag=true;
			else 
				flag=false;
			
	return flag;
	}
}
