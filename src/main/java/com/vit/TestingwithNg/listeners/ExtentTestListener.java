package com.vit.TestingwithNg.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestListener implements ITestListener {
    
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("=========================================");
        System.out.println("Test Starting: " + result.getMethod().getMethodName());
        System.out.println("=========================================");
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("PASSED: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("FAILED: " + result.getMethod().getMethodName());
        System.out.println("Error: " + result.getThrowable().getMessage());
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("SKIPPED: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onStart(ITestContext context) {
        System.out.println("\n=== Suite Started: " + context.getName() + " ===\n");
    }
    
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\n=== Suite Finished: " + context.getName() + " ===\n");
    }
}