package com.poc.reusable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatesUtils {
	
	public String addDaysWithTodaysDate(int daysToBeAdded)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Using today's date
		c.add(Calendar.DATE, daysToBeAdded); // Adding 5 days
		String strAddedDate = sdf.format(c.getTime());
		
		return strAddedDate;
		
	}
	
	public String getMonthYearFromDate( String date)
	{
		String[] arr_Date=date.split("-");
		String Str_monthYear=arr_Date[1]+" "+arr_Date[2];
		return Str_monthYear;
		
	}
	
	public String getDayFromDate( String date)
	{
		String[] arr_Date=date.split("-");
		//System.out.println(arr_Date[0]);
		String str_Day=dayformat(arr_Date[0]);
		return str_Day;
		
	}
	
	public static String dayformat(String day)
	{
			if(day.equalsIgnoreCase("01"))
			{
				day="1";
			}
			else if(day.equalsIgnoreCase("02"))
			{
				day="2";
			}
			else if(day.equalsIgnoreCase("03"))
			{
				day="3";
			}
			else if(day.equalsIgnoreCase("04"))
			{
				day="4";
			}
			else if(day.equalsIgnoreCase("05"))
			{
				day="5";
			}
			else if(day.equalsIgnoreCase("06"))
			{
				day="6";
			}
			else if(day.equalsIgnoreCase("07"))
			{
				day="7";
			}
			else if(day.equalsIgnoreCase("08"))
			{
				day="8";
			}
			else if(day.equalsIgnoreCase("09"))
			{
				day="9";
			}
			return day;
		
		
	}


}
