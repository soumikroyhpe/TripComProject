package com.poc.testcaseClass;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.poc.POM.HomeTrip_POM;
import com.poc.POM.LoginTrip_POM;
import com.poc.baseClass.BaseTest;
import com.poc.utils.ExcelUtils;
import com.poc.utils.FolderChecking;

public class TripComTC extends BaseTest{
	
	private String UserStoryName="TripComTC";
	private LoginTrip_POM loginTrip;
	private HomeTrip_POM homeTrip;
	private ExcelUtils eu;
	private ConcurrentHashMap<String, String> dataMap=null;
	private String testcaseID;
	
	@BeforeTest
	public void beforeTest_Setup() throws IOException
	{
		FolderChecking.moveToArchive(prop.getProperty("TripComTC_MoveToArchive"), UserStoryName);
		
	}
	
	@BeforeMethod
	public void all_setup()
	{
		initialize();
		
		String TEST_DATA_PATH= System.getProperty("user.dir") + "/" +prop.getProperty("TripComTCTestDataPath");
		eu=new ExcelUtils(TEST_DATA_PATH);
		loginTrip= new LoginTrip_POM();
		homeTrip= new HomeTrip_POM();
		
		
	}
	
	@Test(priority=1)
	public void TC01() throws IOException, InterruptedException
	{
		driver.get(prop.getProperty("Trip_URL"));
		dataMap=eu.fetchTestDataMap(dataMap, UserStoryName, "TC01");
		testcaseID=dataMap.get("TestCaseID");
		
		loginTrip.loginTrip(UserStoryName, testcaseID, dataMap.get("UserName"), dataMap.get("Password"));
		homeTrip.fn_closeLanguagePopup(UserStoryName, testcaseID);
		homeTrip.fn_selectINR(UserStoryName, testcaseID);
		homeTrip.fn_selectOneWay(UserStoryName, testcaseID);
		homeTrip.enterLocation(UserStoryName, testcaseID, dataMap.get("LeavingForm"), dataMap.get("LeaveingFormText"),
				dataMap.get("GoingTo"),dataMap.get("GoingToText"));
		homeTrip.selectTicketBookingDate(UserStoryName, testcaseID, dataMap.get("BookingFromToday"));
		homeTrip.fn_scrollToEnd(UserStoryName, testcaseID);
		homeTrip.fn_SelectFlighWithLowestFare(UserStoryName, testcaseID);
		
		
	}
	

}
