package com.automation.selenium.orangehr.pageobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import edu.emory.mathcs.backport.java.util.Arrays;

public class Admin {

	private WebDriver driver;
	private boolean flag;
	public static int searchedElementRow=0;
	private String fieldText;
	private By addBtn=By.id("btnAdd");
	private By addUserHeading=By.id("UserHeading");
	private By selectRole=By.id("systemUser_userType");
	private By empName=By.id("systemUser_employeeName_empName");
	private By username=By.id("systemUser_userName");
	private By status=By.id("systemUser_status");
	private By searchstatus=By.id("searchSystemUser_status");
	private By password=By.id("systemUser_password");
	private By confirmpassword=By.id("systemUser_confirmPassword");
	private By saveBtn=By.id("btnSave");
	private By cancelBtn=By.id("btnCancel");
	private By empTable=By.id("resultTable");
	private By actionMsg=By.xpath("//div[@class='message success fadable']");
	private By usernameEmployeeTable=By.xpath("//a[contains(text(),'Username')]");
	//private By empDoesnotExist=By.xpath("//span[contains(text(),'Employee does not exist')]");
	private By searchBtn=By.id("searchBtn");
	private By searchUsername=By.id("searchSystemUser_userName");
	private By noRecordsFound=By.xpath("//td[contains(text(),'No Records Found')]");
	private By deleteButton=By.id("btnDelete");
	private By deleteOkBtn=By.id("dialogDeleteBtn");
	private By deleteConfirmation=By.xpath("//h3[contains(text(),'OrangeHRM - Confirmation Required')]");
	private By userrole=By.id("searchSystemUser_userType");
	private By employeeName=By.id("searchSystemUser_employeeName_empName");
	private By resetButton=By.id("resetBtn");
	private By addJobTitleHeading=By.xpath("//h1[@id='saveHobTitleHeading']");
	
	private By jobTitleName=By.id("jobTitle_jobTitle");
	private By jobDescription=By.id("jobTitle_jobDescription");
	private By note=By.id("jobTitle_note");
	private By jobSpecification=By.xpath("//input[@id='jobTitle_jobSpec']");
	private By payGradeName=By.id("payGrade_name");
	private By addPayGradeHeading=By.xpath("//h1[@id='payGradeHeading']");
	private By assignedCurrencies=By.xpath("//h1[@id='currencyListHeading']");
	private By addCurrency=By.id("btnAddCurrency");
	private By currency=By.id("payGradeCurrency_currencyName");
	private By minimumSal=By.id("payGradeCurrency_minSalary");
	private By maximumSal=By.id("payGradeCurrency_maxSalary");
	private By currencyHeading=By.xpath("//h1[@id='currencyHeading']");
	private By currencySaveBtn=By.id("btnSaveCurrency");
	private By currencyCancelBtn=By.id("cancelButton");
	private By addemploymentStatus=By.xpath("//h1[@id='empStatusHeading']");
	private By employmentGradeName=By.id("empStatus_name");
	private By addJobCategory=By.id("jobCategoryHeading");
	private By jobCategoryNametext=By.id("jobCategory_name");
	private By addWorkshiftHeading=By.id("workShiftHeading");
	private By shiftName=By.id("workShift_name");
	private By shiftFromTime=By.id("workShift_workHours_from");
	private By shiftTimeTo=By.id("workShift_workHours_to");
	private By availableEmployees=By.id("workShift_availableEmp");
	private By assignEmployeeBtn=By.id("btnAssignEmployee");
	private By assignedEmployees=By.id("workShift_assignedEmp");
	private By alreadyExistMsg=By.xpath("//span[contains(text(),'Already exists')]");
	private By organizationName=By.id("organization_name");    
	private By organizationMail=By.id("organization_email");
	private By organizationAddress=By.id("organization_street1");
	private By organizationCity=By.id("organization_city");
	private By organizationProvince=By.id("organization_province");
	private By organizationNote=By.id("organization_note");
	private By organizationCountry=By.id("organization_country");
	private By editSaveBtn=By.id("btnSaveGenInfo");
	private By searchlocationName=By.id("searchLocation_name");
	private By addLocationName=By.id("location_name");
	private By addLocationCountry=By.id("location_country");
	private By addLocationProvince=By.id("location_province");
	private By addLocationCity=By.id("location_city");
	
	public Admin(WebDriver driver) {
		this.driver=driver;
		
	}
	
	public boolean addExistingUser(String employeeName,String uname,String pwd,String expectedMsg) {
		flag=false;
	
		driver.findElement(addBtn).click();
		Select selectElement=new Select(driver.findElement(selectRole));
		if (driver.findElement(addUserHeading).isDisplayed()) {
		selectElement.selectByVisibleText("ESS");
		driver.findElement(empName).sendKeys(employeeName);
		try {
		 if(driver.findElement(By.xpath("//body/div[4]/ul[1]/li[1]")).isDisplayed()) {
				driver.findElement(By.xpath("//body/div[4]/ul[1]/li[1]")).click();
			}
		}
		catch(NoSuchElementException e) 
		{
		  if(driver.findElement(By.xpath("//span[contains(text(),'"+expectedMsg+"')]")).isDisplayed()) { 
			driver.findElement(cancelBtn).click();			  
			  return flag;
		  }
		}
		/*
		 else 
			 System.out.println("Employee name entered");
		*/ 
		driver.findElement(username).sendKeys(uname);
		try {
			if(driver.findElement(By.xpath("//span[contains(text(),'"+expectedMsg+"')]")).isDisplayed()) { 
				driver.findElement(cancelBtn).click();			  
				  return flag;
			  }
		}
		catch(NoSuchElementException e) 
		{
			if (expectedMsg!=null)
			System.out.println("Expected msg: "+expectedMsg+" Not present");
		}
		
		selectElement=new Select(driver.findElement(status));
		selectElement.selectByVisibleText("Enabled");
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(confirmpassword).sendKeys(pwd);
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
		driver.findElement(saveBtn).click();
		if (driver.findElement(saveBtn).isDisplayed())
			driver.findElement(saveBtn).click();
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		 if( driver.findElement(actionMsg).isDisplayed()){
				if(driver.findElement(actionMsg).getText().contains("Successfully Saved"))
				{
					driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
					flag=true;
					
				}

		
		
		}
		else
		System.out.println("Add button not clicked.");
		
		}
	
		return flag;
	}
	
	public boolean sortColumn(){
		flag=false;
		List<String> obtainedList=new ArrayList<>();
		
		List<WebElement> tablerows=driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
		List<WebElement> tablecols=driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr[1]/td"));
		System.out.println("Rows: "+tablerows.size());
		System.out.println("Cols: "+tablecols.size());
		for(int i=1;i<tablerows.size();i++)
		{
			obtainedList.add(driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[2]")).getText());
			System.out.println(driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[2]")).getText());
		}
		Collections.sort(obtainedList);
		driver.findElement(usernameEmployeeTable).click();
		ArrayList<String> sortedList=new ArrayList<>();
		for(int i=1;i<tablerows.size();i++)
		{
			sortedList.add(driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[2]")).getText());
			System.out.println(driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[2]")).getText());
		}
		
		if(sortedList.equals(obtainedList))
			flag=true;
		
		return flag;
		
		
	}
	
	public boolean searchOneField(String Fieldname,String fieldValue){
		flag=false;
		
	
		switch(Fieldname) {
		
		case "Username" :
			driver.findElement(searchUsername).sendKeys(fieldValue);
			break;
		case "User Role":
			Select select=new Select(driver.findElement(userrole));
			select.selectByVisibleText(fieldValue);
			break;
		default:
			System.out.println("Fieldname not present");
		}
		
		driver.findElement(searchBtn).click();
		try {
			
			if(driver.findElement(noRecordsFound).isDisplayed()) {
				System.out.println("No Records Found");
			}
		}
		 catch(NoSuchElementException e) {
			int tablerows=driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr")).size();
				if(tablerows > 0){
					System.out.println("Number of elements found: "+tablerows);
					if(Fieldname.equals("Username")) {
					for(int i=1;i<=tablerows;i++) {
						
						if((driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[2]")).getText()).equals(fieldValue))
						{
							//searchedElementRow=i;
							flag=true;
							break;
						}
						
					}
					}	
					
					
					else if(Fieldname.equals("User Role")) {
						for(int i=1;i<=tablerows;i++) {
							
							if((driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[3]")).getText()).equals(fieldValue))
							{
								//searchedElementRow=i;
								flag=true;
								break;
							}
							
						}
						}	
						
				}
		
		 }
		return flag;
		}
		
		
	public boolean searchTwoFields(String fieldname1,String fieldname2,String fieldval1,String fieldval2) {
		
		searchElementOperation(fieldname1,fieldval1);
		searchElementOperation(fieldname2,fieldval2);
		driver.findElement(searchBtn).click();
		try {
			
			if(driver.findElement(noRecordsFound).isDisplayed()) {
				System.out.println("No Records Found");
			}
		}
		 catch(NoSuchElementException e) {
			int tablerows=driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr")).size();
				if(tablerows > 0){
					System.out.println("Number of elements found: "+tablerows);
					
					for(int i=1;i<=tablerows;i++) {
					if(fieldname1.equals("Username") || (fieldname2.equals("Username"))) {
						fieldText=driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[2]")).getText();
						if((fieldText.equals(fieldval1)) || (fieldText.equals(fieldval2)) )
						{
							//searchedElementRow=i;
							flag=true;
							break;
						}
					}
					else if(fieldname1.equals("Employee Name") || (fieldname2.equals("Employee Name"))) {
						fieldText=driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[4]")).getText();
						if((fieldText.equals(fieldval1)) || (fieldText.equals(fieldval2)) )
						{
							//searchedElementRow=i;
							flag=true;
							break;
						}
					}
					
					
					
					}
					
				}
		
		 }
		return flag;
		}
		
	public boolean searchElementOperation(String fieldname,String fieldValue) {
		flag=false;
		
		switch(fieldname) {
		
		case "Username" :
			driver.findElement(searchUsername).sendKeys(fieldValue);
			flag=true;
			break;
		case "User Role":
			Select select=new Select(driver.findElement(userrole));
			select.selectByVisibleText(fieldValue);
			flag=true;
			break;
		case "Employee Name" :
			driver.findElement(employeeName).sendKeys(fieldValue);
			flag=true;
			break;
		default:
			System.out.println("Fieldname not present");
		}
		return flag;
	}
	
	
	
	
	public boolean deleteEmployee() {
		flag=false;
		int tablerows=driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr")).size();
		if(tablerows==1) {
			if(driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[1]/td[2]/preceding-sibling::td/input[@type='checkbox']")).isDisplayed()) {
				driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[1]/td[2]/preceding-sibling::td/input[@type='checkbox']")).click();
			}
		}
		else if(tablerows>1) {
				
				for(int i=1;i<=tablerows;i++) {
					
					if(driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[2]/preceding-sibling::td/input[@type='checkbox']")).isDisplayed()) {
						driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[2]/preceding-sibling::td/input[@type='checkbox']")).click();
					}
					
				}
				
			}
			
		
		
			
		driver.findElement(deleteButton).click();
		if(driver.findElement(deleteConfirmation).isDisplayed()){
		driver.findElement(deleteOkBtn).click();
		 if( driver.findElement(actionMsg).isDisplayed()){
				if(driver.findElement(actionMsg).getText().contains("Successfully Deleted"))
				{
					driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
					flag=true;
					
				}

		
		
		}

		}
		
		return flag;
		
	}
	
	
public boolean resetSearchValues() {
	flag=false;
	driver.findElement(resetButton).click();
	System.out.println(driver.findElement(searchUsername).getText().isEmpty());

	if(((driver.findElement(searchUsername).getText()).isEmpty())&&((driver.findElement(userrole).getText()).contains("All"))&&(driver.findElement(employeeName).getText().isEmpty())&&((driver.findElement(searchstatus).getText()).contains("All")) ){
		
		System.out.println("Values reset");
		flag=true;
	}
	return flag;
}

public boolean jobAdd(String jobName,String jobDesc,String jobSpec,String jobNote) {
	flag=false;
	driver.findElement(addBtn).click();
	driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
	if(driver.findElement(addJobTitleHeading).isDisplayed()) {
		driver.findElement(jobTitleName).sendKeys(jobName);
		driver.findElement(jobDescription).sendKeys(jobDesc);
		driver.findElement(jobSpecification).sendKeys(jobSpec);
		driver.findElement(note).sendKeys(jobNote);
		
		driver.findElement(saveBtn).click();
		if(jobName.isEmpty()) {
			
			if(driver.findElement(By.xpath("//span[contains(text(),'Required')]")).isDisplayed()) {
				driver.findElement(cancelBtn).click();
				return flag;
			}
				
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		 if( driver.findElement(actionMsg).isDisplayed()){
				if(driver.findElement(actionMsg).getText().contains("Successfully Saved"))
				{
					driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
					flag=true;
					
				}
		
		}
		
		
		}
	return flag;
	}
	
	public boolean searchTitle(String jobTitle) {
		flag=false;
		int tablerows=driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr")).size();
		if(tablerows>0) {
			for(int i=1;i<=tablerows;i++) {
				
				if((driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[2]")).getText()).equals(jobTitle))
				{
					if(driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[2]/preceding-sibling::td/input[@type='checkbox']")).isDisplayed()) {
						driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[2]/preceding-sibling::td/input[@type='checkbox']")).click();
					}
					flag=true;
					break;
				}
		
		}
		
	}
		return flag;
 }
	
	public boolean deleteTitle() {
		flag=false;
		driver.findElement(deleteButton).click();
		if(driver.findElement(deleteConfirmation).isDisplayed()){
			driver.findElement(deleteOkBtn).click();
			 if( driver.findElement(actionMsg).isDisplayed()){
					if(driver.findElement(actionMsg).getText().contains("Successfully Deleted"))
					{
						driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
						flag=true;
						
					}			
			}

			}
			
			return flag;
			
		}
		
	public boolean payGradeAdd(String payName) {
		flag=false;
		driver.findElement(addBtn).click();
		if(driver.findElement(addPayGradeHeading).isDisplayed()) {
		driver.findElement(payGradeName).sendKeys(payName);
		driver.findElement(saveBtn).click();
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
		 if( driver.findElement(actionMsg).isDisplayed()){
				if(driver.findElement(actionMsg).getText().contains("Successfully Saved"))
				{
					driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
					flag=true;
					
				}			
		}

		}
		return flag;
	}
	
	public boolean selectTitle(String titleName) {
		flag=false;
		
		List<WebElement> tablerows=driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
		for(int i=1;i<=tablerows.size();i++) {
			if(driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[2]")).getText().equals(titleName)){
				driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[2]/a")).click();
				flag=true;
			}
			
		}
		
		return flag;
		
	}
	
	
	public boolean addAssignedCurrencies(String currencyName,String minSalary,String maxSalary,String expectedMsg) {
		flag=false;
		if(driver.findElement(assignedCurrencies).isDisplayed()) {
			driver.findElement(addCurrency).click();
			if(driver.findElement(currencyHeading).isDisplayed()) {
				
				driver.findElement(currency).sendKeys(currencyName);
				try {
					 if(driver.findElement(By.xpath("//body/div[4]/ul[1]/li[1]")).isEnabled()) {
							driver.findElement(By.xpath("//body/div[4]/ul[1]/li[1]")).click();
						}
					}
					catch(NoSuchElementException e) 
					{
					  if(driver.findElement(By.xpath("//span[contains(text(),'Invalid')]")).isDisplayed()) { 
						driver.findElement(currencyCancelBtn).click();		  
						  return flag;
					  }
					}
				
				
				driver.findElement(minimumSal).sendKeys(minSalary);
				try {
					if(driver.findElement(By.xpath("//span[contains(text(),'"+expectedMsg+"')]")).isDisplayed()) { 
						driver.findElement(currencyCancelBtn).click();			  
						  return flag;
					  }
				}
				catch(NoSuchElementException e) 
				{
					if (expectedMsg!=null)
					System.out.println("Expected msg: "+expectedMsg+" Not present.Number Entered Successfully!");
				}
				
				
				driver.findElement(maximumSal).sendKeys(maxSalary);
				try {
					if(driver.findElement(By.xpath("//span[contains(text(),'"+expectedMsg+"')]")).isDisplayed()) { 
						driver.findElement(currencyCancelBtn).click();			  
						  return flag;
					  }
				}
				catch(NoSuchElementException e) 
				{
					if (expectedMsg!=null)
					System.out.println("Expected msg: "+expectedMsg+" Not present.Number Entered Successfully!");
				}
				driver.findElement(currencySaveBtn).click();
				 if( driver.findElement(actionMsg).isDisplayed()){
						if(driver.findElement(actionMsg).getText().contains("Successfully Saved"))
						{
							driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
							flag=true;
							
						}			
				}

			}
				
		}
		return flag;
	}
	
	public boolean addEmployStatus(String empGradeName) {
		flag=false;
		driver.findElement(addBtn).click();
		if(driver.findElement(addemploymentStatus).isDisplayed()) {
		driver.findElement(employmentGradeName).sendKeys(empGradeName);
		driver.findElement(saveBtn).click();
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
		 if( driver.findElement(actionMsg).isDisplayed()){
				if(driver.findElement(actionMsg).getText().contains("Successfully Saved"))
				{
					driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
					flag=true;
					
				}			
		}

		
	}
		return flag;
  }
	
	
	
	
	public boolean editEmpStatus(String newEmpStatusName) {
		flag=false;
		driver.findElement(employmentGradeName).clear();
		driver.findElement(employmentGradeName).sendKeys(newEmpStatusName);
		driver.findElement(saveBtn).click();
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
		 if( driver.findElement(actionMsg).isDisplayed()){
				if(driver.findElement(actionMsg).getText().contains("Successfully Saved"))
				{
					driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
					flag=true;
					
				}			
		}
		 	return flag;
	}

	public boolean addjobCategories(String jobCategoryName) {
		flag=false;
		driver.findElement(addBtn).click();
		if(driver.findElement(addJobCategory).isDisplayed()) {
		driver.findElement(jobCategoryNametext).sendKeys(jobCategoryName);
		driver.findElement(saveBtn).click();
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
		 if( driver.findElement(actionMsg).isDisplayed()){
				if(driver.findElement(actionMsg).getText().contains("Successfully Saved"))
				{
					driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
					flag=true;
					
				}			
		}

		
	}
		return flag;
  }

	public boolean editJobCategory(String newJobCategoryName) {
		flag=false;
		driver.findElement(jobCategoryNametext).clear();
		driver.findElement(jobCategoryNametext).sendKeys(newJobCategoryName);
		driver.findElement(saveBtn).click();
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
		 if( driver.findElement(actionMsg).isDisplayed()){
				if(driver.findElement(actionMsg).getText().contains("Successfully Saved"))
				{
					driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
					flag=true;
					
				}			
		}
		 	return flag;
	}

 public boolean addWorkShift(String workShiftName,String fromTime,String toTime,List<String> empList){
	 flag=false;
	 	WebDriverWait wait=new WebDriverWait(driver,30);
	 	wait.until(ExpectedConditions.elementToBeClickable(addBtn));
		driver.findElement(addBtn).click();
		if(driver.findElement(addWorkshiftHeading).isDisplayed()) {
		driver.findElement(shiftName).sendKeys(workShiftName);
		try {
			if(driver.findElement(alreadyExistMsg).isDisplayed()) {
				driver.findElement(cancelBtn).click();
				return false;
			}
		}
		catch(NoSuchElementException e) 
		{
		
		Select select=new Select(driver.findElement(shiftFromTime));
		select.selectByVisibleText(fromTime);
		select=new Select(driver.findElement(shiftTimeTo));
		select.selectByVisibleText(toTime);
		select=new Select(driver.findElement(availableEmployees));
		List<WebElement> empAvailable=select.getOptions();
		for(String emp: empList) {
			for(WebElement element : empAvailable ) {
				if(element.getText().contains(emp)) {
					select.selectByVisibleText(emp);
					driver.findElement(assignEmployeeBtn).click();
				}
				
			}
			
		}
		
		select=new Select(driver.findElement(assignedEmployees));
				
		if(!((select.getOptions().size())== empList.size())) {
			
			System.out.println("Employee not in List");
			return false;
			
		}
				
		driver.findElement(saveBtn).click();
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
		 if( driver.findElement(actionMsg).isDisplayed()){
				if(driver.findElement(actionMsg).getText().contains("Successfully Saved"))
				{
					driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
					flag=true;
					
				}			
		}
		}
		
	}
		return flag;

	 
 }
 
public boolean fieldsPrePopulated(){
	flag=false;
	By[] byArray =new By[] { organizationName,organizationMail,organizationAddress,organizationCity,organizationProvince,organizationNote,organizationCountry};
	
	//List<By> orgsList =Arrays.asList(byArray);
	
	for(By ele: byArray) {
		System.out.println(driver.findElement(ele).getAttribute("value"));
		if(!(driver.findElement(ele).getAttribute("value").isEmpty())) {
			
			flag=true;
		}
		else {
			System.out.println(driver.findElement(ele).getAttribute("name")+" Field not prepopulated!");
			flag=false;
			break;
		}
		
	}
	
	return flag;	
	
	}
 public WebElement fieldLocatorGeneralInfo(String fieldname) {
	 WebElement element = null;
	 switch(fieldname) {
	 
	 case "Organization Name":
		 element=driver.findElement(organizationName);
		 break;
		

	 case "Organization Mail":
		 element=driver.findElement(organizationMail);
		 break;
		

	 case "Organization Address":
		 element=driver.findElement(organizationAddress);
		 break;
		 

	 case "Organization City":
		 element=driver.findElement(organizationCity);
		 break;
		

	 case "Organization Province":
		 element=driver.findElement(organizationProvince);
		 break;
		
		
	 case "Organization Note":
		 element=driver.findElement(organizationNote);
		 break;
		

	 case "Organization Country":
		 element=driver.findElement(organizationCountry);
		 break;
	 }
	 return element;
 }
public boolean editFieldsGeneralInformation(List<String> fieldname,List<String> fieldValue) {
	flag=false;
	
	if(!(fieldname.isEmpty()) && (!(fieldValue.isEmpty()))) {
		
		driver.findElement(editSaveBtn).click();
		for(int i=0;i<fieldname.size();i++) {
			WebElement fieldName=fieldLocatorGeneralInfo(fieldname.get(i));
			fieldName.clear();
			fieldName.sendKeys(fieldValue.get(i));
		}
		
		
	}
	
	
	driver.findElement(editSaveBtn).click();
	
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
	 if( driver.findElement(actionMsg).isDisplayed()){
			if(driver.findElement(actionMsg).getText().contains("Successfully Saved"))
			{
				driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
				flag=true;
				
			}

			else
				System.out.println("Save button not clicked.");
				
	
	}
	
	

	return flag;
	
	
}
}

