package com.poc.POM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.poc.baseClass.BaseTest;
import com.poc.reusable.DatesUtils;
import com.poc.reusable.GenericFunction;

public class HomeTrip_POM extends BaseTest {
	
	private String className_local=this.getClass().getName();
	GenericFunction gf= new GenericFunction();
	DatesUtils du = new DatesUtils();
	
	
	public HomeTrip_POM()
	{
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//span[@class='close-icon fi-close fi']")
	private WebElement lnk_home_SuggestedLanguages_Close;
	
	@FindBy(xpath="//a[@title='My Bookings']//parent::div//preceding::div/div/div[contains(text(),'USD')]")
	private WebElement lnk_home_Currency_USD;
	
	@FindBy(xpath="//span[contains(text(),'Indian Rupee')]//parent::div")
	private WebElement lnk_home_Currency_INR;
	
	@FindBy(xpath="//span[contains(text(),'Flights')]//parent::li")
	private WebElement lnk_home_Flights;
	
	@FindBy(xpath="//div[@data-testid='flightType']")
	private WebElement btn_home_FlightType;
	
	@FindBy(xpath="//li[@data-testid='flightType_OW']")
	private WebElement btn_home_FlightType_Oneway;
	
	@FindBy(xpath="//i[@data-testid='cityLabel_delete_0']")
	private WebElement btn_home_OneWaySelected_Form;
	
	@FindBy(xpath="//span[contains(text(),'Leaving from')]//parent::div")
	private WebElement btn_home_OneWaySelected_LeavingForm;
	
	@FindBy(xpath="//input[@placeholder='Leaving from' and @data-testid='search_city_from0']")
	private WebElement txt_home_OneWaySelected_LeavingForm;
	
	@FindBy(xpath="//span[contains(text(),'Netaji Subhas Chandra Bose International Airport')]")
	private WebElement lnk_home_OneWaySelected_LeavingForm_CCU;
	
	@FindBy(xpath="//span[contains(text(),'Going to')]//parent::div")
	private WebElement btn_home_OneWaySelected_GoingTo;
	
	@FindBy(xpath="//input[@placeholder='Going to' and @data-testid='search_city_to0']")
	private WebElement txt_home_OneWaySelected_GoingTo;
	
	@FindBy(xpath="//span[contains(text(),'Indira Gandhi International Airport')]")
	private WebElement lnk_home_OneWaySelected_GoingTo_DELHI;

	@FindBy(xpath="//input[@data-testid='search_date_depart0']")
	private WebElement cal_home_OneWaySelected_BookingDate;

	 
	
	 	
	
	
	
	public void fn_closeLanguagePopup(String userstoryName,String testcaseID)
	{
		try {
			gf.click(lnk_home_SuggestedLanguages_Close);
		}
		catch (Exception e) {
			System.out.println("SuggestedLanguages popup is not available !!!!");
		}
	}
	
	public void fn_selectINR(String userstoryName,String testcaseID) throws InterruptedException
	{
		gf.click(lnk_home_Currency_USD);
		gf.click(lnk_home_Currency_INR);
		gf.captureExtentReport_HTMLReport_log(className_local, "Successfully Selected Currency as INR.");
	}
	
	public void fn_selectOneWay(String userstoryName,String testcaseID) throws InterruptedException, IOException
	{
		gf.click(lnk_home_Flights);
		gf.click(btn_home_FlightType);
		gf.click(btn_home_FlightType_Oneway);
		try {
			gf.click(btn_home_OneWaySelected_Form);
		}
		catch (Exception e) {
			
		}
		gf.captureExtentReportwithSS_HTMLReport_log(className_local, "Successfully selected Oneway!!");
		gf.takeScreenshot(userstoryName, testcaseID, "OneWay_Selected.png");
	}
	
	public void enterLocation(String userstoryName, String testcaseID, String leavingForm, String leavingFormtext,
			String goingTo, String goingToText) throws InterruptedException, IOException {
		gf.click(btn_home_OneWaySelected_LeavingForm);
		gf.input(txt_home_OneWaySelected_LeavingForm, leavingForm);
		gf.click(driver.findElement(By.xpath("//span[contains(text(),'" + leavingFormtext + "')]")));
		gf.captureExtentReport_HTMLReport_log(className_local, "Successfully selected Leaving Form airport : "+leavingFormtext);
		gf.click(btn_home_OneWaySelected_GoingTo);
		gf.input(txt_home_OneWaySelected_GoingTo, goingTo);
		// gf.click(lnk_home_OneWaySelected_GoingTo_DELHI);
		gf.click(driver.findElement(By.xpath("//span[contains(text(),'" + goingToText + "')]")));
		gf.captureExtentReport_HTMLReport_log(className_local, "Successfully selected Going to airport : "+goingToText);
		
	}
	
	public void selectTicketBookingDate(String userstoryName, String testcaseID,String daysToBeAdded) throws InterruptedException, IOException
	{
		int int_daysToBeAdded=Integer.parseInt(daysToBeAdded);  
		String str_TicketBookingDate = du.addDaysWithTodaysDate(int_daysToBeAdded);
		gf.captureExtentReport_HTMLReport_log(className_local, "Ticket booking date : "+str_TicketBookingDate);
		String day1= du.getDayFromDate(str_TicketBookingDate);
		String month1= du.getMonthYearFromDate(str_TicketBookingDate);
		
		gf.scrollToElement(cal_home_OneWaySelected_BookingDate);
		gf.click(cal_home_OneWaySelected_BookingDate);
		
		
		while(true) {
			String text1=driver.findElement(By.xpath("(//div[@class='c-calendar-month__title'])[1]")).getText().trim();
			
			System.out.println(text1);
			if (text1.equalsIgnoreCase(month1)) {
				break;
			}
			else 
			{
				gf.click(driver.findElement(By.xpath("//span[@class='c-calendar-icon-next']")));
			}
		}
		gf.click(driver.findElement(By.xpath("(//span[@class='day' and contains(text(),'"+day1+"')])[1]")));
		
		gf.captureExtentReportwithSS_HTMLReport_log(className_local, "Successfully added flight search criteria.");
		gf.takeScreenshot(userstoryName, testcaseID, "Flight_SearchDetails.png");
		
		driver.findElement(By.xpath("//div[@data-testid='search_btn']")).click();

		//To allow popup in flight search result page
		try {
			gf.click(driver.findElement(By.xpath("//div[@class='popup-btn' and contains(text(),'Allow')]")));
		} catch (Exception e) {
			
		}
		
		
	}
	
	public void fn_scrollToEnd(String userstoryName, String testcaseID) throws InterruptedException {

		int i = 100;
		while (i <= 1800) {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + i + ")");
			Thread.sleep(700);
			i = i + 30;
		}

		int j = 3000;
		while (j >= 200) {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-" + j + ")");
			Thread.sleep(50);
			j = j - 20;
		}

	}
	
	public void fn_SelectFlighWithLowestFare(String userstoryName, String testcaseID) throws InterruptedException, IOException
	{
		ArrayList<Integer> price = new ArrayList<Integer>();
		int noFlights=driver.findElements(By.xpath("//div[@class='select-area-price']//div/span")).size();
		
		for(int i=1;i<=noFlights;i++)
		{
			String flight_fear=driver.findElement(By.xpath("(//div[@class='select-area-price']//div/span)["+i+"]")).getText().trim();
			System.out.println(i+" no.flight with fear : "+flight_fear);
			price.add(Integer.parseInt(flight_fear.replaceAll("[INR,]", "").trim()));
			
		}
		//System.out.println(price);
		Collections.sort(price);
		//System.out.println(price);
		gf.captureExtentReport_HTMLReport_log(className_local, "Lowest flight fare: "+price.get(0));
		//driver.findElement(By.xpath("(((//span[@data-price='"+price.get(0)+"'])[1]//parent::div//parent::div)[1]//following::div)[1]")).click();
		gf.captureExtentReportwithSS_HTMLReport_log(className_local, "Available flights.");
		gf.takeScreenshot(userstoryName, testcaseID, "Available_flights.png");
		gf.click(driver.findElement(By.xpath("(((//span[@data-price='"+price.get(0)+"'])[1]//parent::div//parent::div)[1]//following::div)[1]")));
		Thread.sleep(7000);
		gf.captureExtentReportwithSS_HTMLReport_log(className_local, "Successfully found flight with lowest fare.");
		gf.takeScreenshot(userstoryName, testcaseID, "flightwithlowestFare.png");
		
		gf.click(driver.findElement(By.xpath("((((//span[@data-price='"+price.get(0)+"'])[1]//parent::div//parent::div)[1]//following::div)[1]//span)[1]/button")));
	
		gf.captureExtentReportwithSS_HTMLReport_log(className_local, "Successfully clicked on book option!!");
		gf.takeScreenshot(userstoryName, testcaseID, "AddPassengerPage.png");
		
	}
	

}
