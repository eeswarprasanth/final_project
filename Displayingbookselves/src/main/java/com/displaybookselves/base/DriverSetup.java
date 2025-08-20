package com.displaybookselves.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverSetup {

    private static WebDriver driver;
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream("config/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WebDriver initializeDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(); // Assumes chromedriver is in PATH
                break;

            case "edge":
                System.setProperty("webdriver.edge.driver", properties.getProperty("edge.driver.path"));
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
