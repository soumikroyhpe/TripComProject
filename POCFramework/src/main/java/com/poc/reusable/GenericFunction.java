package com.poc.reusable;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.poc.baseClass.BaseTest;
import com.poc.reports.ExtentTestManager;
import com.poc.utils.Screenshot;

;

public class GenericFunction extends BaseTest{
	
	public Logger logger= LogManager.getLogger(GenericFunction.class);
	
	String className_local=this.getClass().getName();
	
	private WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(30));
	
	Screenshot takeSnipOf= new Screenshot();
	 
	String screenshotpath = prop.getProperty("ProjectHomePath")+ prop.getProperty("screenshotPath");
	
	

	
	
	
	
	public void click(WebElement element) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
		logger.info(className_local+" : Clicked on the element: "+element);
		Thread.sleep(2000);
	}
	
	public void jsClick(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
		logger.info(className_local+" : Clicked on the element: "+element);
		
	}
	
	public void input(WebElement element, String Value) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(Value.trim());
		Thread.sleep(2000);
		logger.info(className_local+" : Entered value: "+Value+" into the element: "+element);
	}
	
	public void clearText(WebElement element) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		Thread.sleep(1000);
	}
	
	public void clearTextAndInput(WebElement element, String Value) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(element));
		clearText(element);
		input(element, Value);
	}
	
	public void scrollToElement(WebElement element) throws InterruptedException
	{
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
		logger.info(className_local+" : Successfully scrolled till the element: "+element);
		Thread.sleep(1000);
	}
	
	public void selectByVisibleText(WebElement element, String text) throws InterruptedException
	{
		Select obj= new Select(element);
		obj.selectByVisibleText(text.trim());
		Thread.sleep(1000);
		logger.info(className_local+" : Selected value: "+text+" from dropdown list : "+element);
	}
	
	public void captureExtentReportwithSS_HTMLReport_log(String className_local,String message)
	{
		logger.info(className_local +" : "+message);
		Reporter.log(message);
		ExtentTestManager.getTest().pass(message,
				MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenCaptureBase_64()).build());
		
	}
	
	public void captureExtentReport_HTMLReport_log(String className_local,String message)
	{
		logger.info(className_local +" : "+message);
		ExtentTestManager.reporterLog(message);
		
		
	}
	
	
	
	public void fn_WaitTillvisibilityOfElement(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public String getTextOfELement(WebElement element) {
		fn_WaitTillvisibilityOfElement(element);
		String text = "";
		text = element.getText().trim();

		//logger.info(className_local + " : Successfully extracted the text from: " + element);
		return text;

	}
	
	public void validateTextField(WebElement element,String testDataVal, String fieldName)
	{
		if(!testDataVal.trim().equalsIgnoreCase("NA"))
		{
			if (testDataVal.trim().equalsIgnoreCase(getTextOfELement(element))) {
				
				
				  logger.info(className_local + " : Successfully validated--> " + fieldName +
				 " ,Expected--> " + testDataVal + ", Actual--> "+getTextOfELement(element));
				 
				
				Assert.assertTrue(true, className_local + " : Successfully validated--> " + fieldName + " ,Expected--> "
						+ testDataVal + ", Actual--> "+getTextOfELement(element));
			}
			else
			{
				
				  logger.info(className_local + " : Unsuccessfully validated--> " + fieldName +
				  " ,Expected--> " + testDataVal + ", Actual--> "+getTextOfELement(element));
				 
				
				Assert.assertTrue(true, className_local + " : Unsuccessfully validated--> " + fieldName + " ,Expected--> "
						+ testDataVal + ", Actual--> "+getTextOfELement(element));
			}
		}
		
	}
	
	public void takeScreenshot(String userstoryName,String testcaseID,String ScreenshotName) throws IOException
	{
		takeSnipOf.driverTakeScreenshot(
				driver, screenshotpath + userstoryName.trim() + "/" + testcaseID.trim() + "/"
						+ takeSnipOf.secondsMinutes() + "_" + ScreenshotName,
				testcaseID.trim(), userstoryName.trim());
	}
	

}
