package com.poc.rough;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateAdd {
	
	public static void main(String[] args) {
		
		/*
		 * SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		 * 
		 * Date date = new Date(); System.out.println(formatter.format(date));
		 */  
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Using today's date
		c.add(Calendar.DATE, 365); // Adding 5 days
		String output = sdf.format(c.getTime());
		System.out.println(output);
		String[] date=output.split("-");
		System.out.println(date[0]);
		System.out.println(date[1]);
		System.out.println(date[2]);
		
		String monthYear=date[1]+" "+date[2];
		String Str_Day=dayformat(date[0]);
		System.out.println(monthYear);
		System.out.println(Str_Day);
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
