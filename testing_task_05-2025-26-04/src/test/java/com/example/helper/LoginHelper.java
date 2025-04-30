package com.example.helper;

import com.example.ApplicationManager;
import com.example.model.UserData;
import org.junit.Assert;
import org.openqa.selenium.By;

import static java.lang.Thread.sleep;

public class LoginHelper extends HelperBase {
    public LoginHelper(ApplicationManager appManager) {
        super(appManager);
    }

    public void login(UserData user) {
        appManager.getNavigationHelper().openMainPage();
        clickOnLoginButton();
        enterCredentials(user);
        submitLoginButton();
    }

    public void clickOnLoginButton() {
        driver.findElement(By.cssSelector("a.s-header-item__link.s-header-item__link--login")).click();
    }

    public void enterCredentials(UserData user) {
        driver.findElement(By.id("user")).clear();
        driver.findElement(By.id("user")).sendKeys(user.getUsername());
        driver.findElement(By.id("lj_loginwidget_password")).click();
        driver.findElement(By.id("lj_loginwidget_password")).clear();
        driver.findElement(By.id("lj_loginwidget_password")).sendKeys(user.getPassword());
    }

    public void submitLoginButton() {
        driver.findElement(By.name("action:login")).click();
    }

    public void checkUserIsLoggedIn() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String usernameSelector = "a.s-header-item__link.s-header-item__link--user";
        String friendsSelector = "a.s-header-item__link.s-header-item__link--friends.s-nav-rootlink-feed";
        Assert.assertTrue(isElementPresent(By.cssSelector(friendsSelector)));
        Assert.assertTrue(isElementPresent(By.cssSelector(usernameSelector)));
        Assert.assertEquals("ELINBEK", driver.findElement(By.cssSelector(usernameSelector)).getText());
    }
}
