package com.automation.selenium.orangehr.listeners;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automation.selenium.orangehr.base.TestSetUpBase;

import io.qameta.allure.Attachment;

public class AllureReportListener implements ITestListener {
	private WebDriver driver;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

@Override
	public void onTestFailure(ITestResult result) {

		//Object obj = result.getAttributeNames();

		//Object webDriverAttribute = result.getAttribute("m_instance");

		TestSetUpBase newobj = (TestSetUpBase)result.getMethod().getInstance();


		 
		saveScreenshot( newobj.getDriver());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Attachment(value = "Page Screenshot", type = "image/png")
	public byte[] saveScreenshot(WebDriver driver) {
		
		byte[] imageByteArray = (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		return imageByteArray;
	}

}
