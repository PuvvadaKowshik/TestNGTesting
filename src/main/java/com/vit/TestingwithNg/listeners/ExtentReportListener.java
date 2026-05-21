package com.vit.TestingwithNg.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.vit.TestingwithNg.utils.ScreenshotsUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {
    
    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    
    public static void setDriver(WebDriver driver) {
        driverThread.set(driver);
    }
    
    public static ExtentTest getTest() {
        return extentTest.get();
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        extentTest.set(test);
        test.log(Status.INFO, "Test Started: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = extentTest.get();
        WebDriver driver = driverThread.get();
        
        test.log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
        
        String screenshotPath = ScreenshotsUtils.captureScreenshot(driver, result.getMethod().getMethodName(), "PASSED");
        if (screenshotPath != null) {
            try {
                test.addScreenCaptureFromPath(screenshotPath, "Success Screenshot");
                test.log(Status.INFO, "Screenshot attached: " + screenshotPath);
            } catch (Exception e) {
                test.log(Status.WARNING, "Could not attach screenshot: " + e.getMessage());
            }
        }
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = extentTest.get();
        WebDriver driver = driverThread.get();
        
        test.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        test.log(Status.FAIL, "Failure Reason: " + result.getThrowable().getMessage());
        
        String screenshotPath = ScreenshotsUtils.captureScreenshot(driver, result.getMethod().getMethodName(), "FAILED");
        if (screenshotPath != null) {
            try {
                test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
                test.log(Status.INFO, "Screenshot attached: " + screenshotPath);
            } catch (Exception e) {
                test.log(Status.WARNING, "Could not attach screenshot: " + e.getMessage());
            }
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = extentTest.get();
        test.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
        if (result.getThrowable() != null) {
            test.log(Status.SKIP, "Skip Reason: " + result.getThrowable().getMessage());
        }
    }
    
    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
            System.out.println("Extent Report generated successfully");
        }
    }
}