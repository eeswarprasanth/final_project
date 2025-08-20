package com.displaybookselves.utils;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PopupHandler {
    WebDriver driver;
    WebDriverWait wait;

    public PopupHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void closePopupIfVisible() {
        try {
            WebElement closeBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a.close-reveal-modal.hide-mobile")));

            closeBtn.click();
            System.out.println("Popup closed.");
        } catch (Exception e) {
            System.out.println("Popup not visible.");
        }
    }
}
