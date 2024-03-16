package com.poc.rough;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class StringModification {
	
	public static void main(String[] args) {
		
		String str="INR 207,963";
		
		String n=str.replaceAll("[INR,]", "").trim();
		System.out.println(n);
		
		ArrayList<Integer> price = new ArrayList<Integer>();
		price.add(Integer.parseInt(n));
		price.add(Integer.parseInt("9783"));
		price.add(Integer.parseInt("1209893"));
		price.add(Integer.parseInt("11"));
		
		System.out.println(price);
		Collections.sort(price);
		System.out.println(price);
		System.out.println(price.get(0));
	}

}
