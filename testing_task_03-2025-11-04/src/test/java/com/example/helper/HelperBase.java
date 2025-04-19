package com.example.helper;

import com.example.ApplicationManager;
import org.openqa.selenium.*;

public class HelperBase {
    protected WebDriver driver;
    protected boolean acceptNextAlert = true;
    protected ApplicationManager appManager;

    public HelperBase(ApplicationManager appManager) {
        this.appManager = appManager;
        this.driver = appManager.getDriver();
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
