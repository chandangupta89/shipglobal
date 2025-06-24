package utility;

import java.io.File;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import java.lang.reflect.Method;

import com.google.common.io.Files;

public class TestListener implements ITestListener {
	String concatenate = ".";
	ExtentReports extent;
	ExtentTest logger;
	

	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");

	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));

		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
		
	}

	public void onTestSuccess(ITestResult result) {

		ExtentTestManager.getTest().log(Status.PASS, result.getName() + "  " + "Test_Case passed");
	}

	public void onTestFailure(ITestResult result) {

		System.out.println("*** Test " + result.getName() + " Test_Case Failed...");
		try {
			ExtentTestManager.getTest().log(Status.FAIL, result.getName() + " " + "Test_Case Failed");
		} catch (Exception ex) {
			System.out.println("Exception errro in t" + ex);
		}
		ExtentTestManager.getTest().log(Status.FAIL, "Test_Case Failed" + result.getThrowable());

		TakesScreenshot ts = (TakesScreenshot) BaseTest.driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String screenshotpath = "./TestReport/screenshots/" + result.getName() +timeStamp+ ".png";
		File destFile = new File(screenshotpath);

		String targetfile = destFile.getAbsolutePath();

		System.out.println(targetfile);

		try {
			Files.copy(srcFile, destFile);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ExtentTestManager.getTest().log(Status.FAIL, "Screenshot has been taken.");
	

	}

	public String getTestClassName(String testName) {

		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		System.out.println("Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");

		ExtentTestManager.getTest().log(Status.SKIP, result.getName() + " " + "Test_Case Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

}
