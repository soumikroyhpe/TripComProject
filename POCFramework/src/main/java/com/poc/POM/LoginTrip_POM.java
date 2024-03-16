package com.poc.POM;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.poc.baseClass.BaseTest;
import com.poc.reusable.GenericFunction;
import com.poc.utils.Screenshot;

import net.bytebuddy.asm.MemberSubstitution.FieldValue;

public class LoginTrip_POM extends BaseTest{
	
	private String className_local=this.getClass().getName();
	GenericFunction gf= new GenericFunction();
	
	
	public LoginTrip_POM()
	{
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//span[@class='mc-hd__login-btn']")
	private WebElement lnk_landing_SigninRegister;
	
	@FindBy(xpath="//input[@placeholder='Please enter an email address']")
	private WebElement txt_landing_SigninRegister_EnterEmail;
	
	@FindBy(xpath="//span[contains(text(),'Continue')]")
	private WebElement btn_landing_SigninRegister_Continue;
	
	@FindBy(xpath="//span[@class='password-login-btn-text']")
	private WebElement lnk_landing_SigninRegister_Continue_SignInWithPassword;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement txt_landing_SigninRegister_Continue_SignInWithPassword_EnterPassword;
	
	@FindBy(xpath = "//button[@id='ibu_login_submit' and @type='submit']/span[contains(text(),'Sign In')]")
	private WebElement btn_landing_SigninRegister_Continue_SignInWithPassword_SignIn;
	
	
	public void loginTrip(String userstoryName,String testcaseID,String username,String password) throws InterruptedException, IOException
	{
		gf.captureExtentReportwithSS_HTMLReport_log(className_local,"Successfully open trip.com!!");
		gf.takeScreenshot(userstoryName, testcaseID, "Beforelogin.png");
		
		gf.click(lnk_landing_SigninRegister);
		
		gf.input(txt_landing_SigninRegister_EnterEmail, username);
		Thread.sleep(2000);
		gf.captureExtentReport_HTMLReport_log(className_local, "Enter username : "+username);
		gf.click(btn_landing_SigninRegister_Continue);
		Thread.sleep(4000);
		gf.click(lnk_landing_SigninRegister_Continue_SignInWithPassword);
		gf.input(txt_landing_SigninRegister_Continue_SignInWithPassword_EnterPassword, password);
		gf.captureExtentReport_HTMLReport_log(className_local, "Enter Password : "+password);
		gf.click(btn_landing_SigninRegister_Continue_SignInWithPassword_SignIn);
		gf.captureExtentReportwithSS_HTMLReport_log(className_local,"Successfully login into trip.com!!");
		gf.takeScreenshot(userstoryName, testcaseID, "Afterlogin.png");
	}

}


