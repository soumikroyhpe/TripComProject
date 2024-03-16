package com.poc.listerner;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.poc.baseClass.BaseTest;
import com.poc.reports.ExtentTestManager;
import com.poc.reusable.GenericFunction;



public class ListenerClass extends BaseTest implements ITestListener {

	private static long endTime;


	private static void setStartTime(long startTime) {

	}

	private static void setEndTime(long endTime) {
		ListenerClass.endTime = endTime;
	}

	public void onTestStart(ITestResult result) {

		System.out.println("---------- Executing :-" + getSimpleMethodName(result)+" ----------------------");
		
		ExtentTestManager.createTest(result.getName(), result.getMethod().getDescription());
		ExtentTestManager.setCategoryName(getSimpleClassName(result));

	}

	public void onTestSuccess(ITestResult result) {
		
		ExtentTestManager.getTest().assignCategory(getSimpleClassName(result));
		
        addExtentLabelToTest(result);
        ExtentTestManager.endTest();

	}

	public void onTestFailure(ITestResult result) {
		
		ExtentTestManager.getTest().fail(result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenCaptureBase_64()).build());
		addExtentLabelToTest(result);
		ExtentTestManager.endTest();

	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		setStartTime(context.getStartDate().getTime());
        setEndTime(context.getEndDate().getTime());
	}

	private synchronized String getSimpleClassName(ITestResult result) {
		return result.getMethod().getRealClass().getSimpleName();
	}

	private synchronized String getSimpleMethodName(ITestResult result) {
		return result.getName();
	}

	private synchronized void addExtentLabelToTest(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS)
			ExtentTestManager.getTest().pass(MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
		else if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getTest().fail(MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
		} else
			ExtentTestManager.getTest().skip(MarkupHelper.createLabel("Test Skipped", ExtentColor.ORANGE));
	}

}
