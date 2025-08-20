package com.displaybookselves.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream("config/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("Failed to load config.properties file: " + e.getMessage());
        }
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    public static String getEdgeDriverPath() {
        return properties.getProperty("edge.driver.path");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicitly.wait"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("page.load.timeout"));
    }

    public static int getScriptTimeout() {
        return Integer.parseInt(properties.getProperty("script.timeout"));
    }

    public static String getAppUrl() {
        return properties.getProperty("app.url");
    }

    public static String getScreenshotPath() {
        return properties.getProperty("screenshot.path");
    }

    public static String getReportPath() {
        return properties.getProperty("report.path");
    }

    public static String getLog4jConfigPath() {
        return properties.getProperty("log4j.config.path");
    }
    
    public static String getSeachWord() {
    	return properties.getProperty("search.keyword");
    }
    
    public static String getWishSearch() {
    	return properties.getProperty("wish.search.keyword");
    }
}
