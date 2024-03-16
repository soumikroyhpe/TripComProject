package com.poc.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.poc.baseClass.BaseTest;

public class Screenshot extends BaseTest{
	
	
	public void driverTakeScreenshot(WebDriver webdriver, String fileWithPath, String TestCaseID,String processname) throws IOException
	{
		String[] arrPath = fileWithPath.split("\\.");
		Date date1= new Date();
		DateFormat dataFormat= new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String strDate= dataFormat.format(date1);
		
		String newDate = strDate.replace(":", "_");
		newDate = newDate.replace("-", "_");
		String strPath = arrPath[0] + "_" + newDate + "." + arrPath[1];
		
		{
			File CreateTestCaseFolder = new File(
					prop.getProperty("ProjectHomePath")+prop.getProperty("CreateTCFolderPath") + processname.trim() + "/" +TestCaseID.trim());
			
			if(CreateTestCaseFolder.exists() == true)
			{
				
			}else
			{
				CreateTestCaseFolder.mkdir();
			}
			
			TakesScreenshot scrShot = ((TakesScreenshot)webdriver);
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(strPath);
			FileUtils.copyFile(SrcFile, DestFile);
		}
		
	}
	
	public String secondsMinutes()
	{
		Date date1 = new Date();
		
		DateFormat dateformat =  new SimpleDateFormat("mmss");
		String secondsMinutes = dateformat.format(date1);
		return secondsMinutes;
		
	}

}
