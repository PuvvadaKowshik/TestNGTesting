package com.vit.TestingwithNg.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotsUtils {
    
    private static final String SCREENSHOT_DIR = "test-output/screenshots/";
    private static final String SUCCESS_DIR = SCREENSHOT_DIR + "success/";
    private static final String FAILURE_DIR = SCREENSHOT_DIR + "failure/";
    
    static {
        new File(SUCCESS_DIR).mkdirs();
        new File(FAILURE_DIR).mkdirs();
    }
    
    public static String captureScreenshot(WebDriver driver, String testName, String status) {
        if (driver == null) {
            System.out.println("Driver is null, cannot capture screenshot");
            return null;
        }
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String filePath;
            String reportPath;
            
            if (status.equalsIgnoreCase("PASSED")) {
                filePath = SUCCESS_DIR + fileName;
                reportPath = "screenshots/success/" + fileName;
            } else {
                filePath = FAILURE_DIR + fileName;
                reportPath = "screenshots/failure/" + fileName;
            }
            
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(filePath);
            FileUtils.copyFile(source, destination);
            
            System.out.println("Screenshot saved: " + filePath);
            System.out.println("Report path: " + reportPath);
            
            return reportPath;
        } catch (IOException e) {
            System.err.println("Screenshot failed: " + e.getMessage());
            return null;
        }
    }
}