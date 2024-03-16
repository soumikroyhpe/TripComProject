package com.poc.baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BaseTest {
	
	protected static WebDriver driver=null;
	protected static Properties prop;
	protected FileInputStream Properties_FIS;
	
	//public Logger logger= LogManager.getLogger(BaseTest.class);
	
	public BaseTest() {
		try {
			Properties_FIS = new FileInputStream(
					System.getProperty("user.dir") + "/PropertyFiles/AppDetails.properties");
			prop = new Properties();
			prop.load(Properties_FIS);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		
	}

	protected void initialize()
	{
		if(prop.getProperty("browserType").equalsIgnoreCase("Chrome"))
		{
			ChromeOptions handleSSL= new ChromeOptions();
			handleSSL.addArguments("--incognito");
			handleSSL.addArguments("--remote-allow-origins=*");
			handleSSL.setAcceptInsecureCerts(true);
			handleSSL.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR,UnexpectedAlertBehaviour.ACCEPT);
			driver= new ChromeDriver(handleSSL);
			//driver= new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		else if(prop.getProperty("browserType").equalsIgnoreCase("Edge"))
		{
			EdgeOptions e= new EdgeOptions();
			/*
			 * e.setAcceptInsecureCerts(true); driver= new EdgeDriver(e);
			 */
			
		
			driver= new EdgeDriver();
			
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
		}
		
	}
	
	protected  String getScreenCaptureBase_64()
	{
		TakesScreenshot scrShot=((TakesScreenshot)driver);
		String base64code=scrShot.getScreenshotAs(OutputType.BASE64);
		return base64code;
		
	}
	
	public static String getParentDir()
	{
		File f = null;
		String parentDir = null;
		try {
			f = new File(System.getProperty("user.dir"));
			parentDir = f.getParent();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return parentDir;
	}
	

}
