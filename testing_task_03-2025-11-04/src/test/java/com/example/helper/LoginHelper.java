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

    public void login(){
        appManager.getNavigationHelper().openMainPage();
        clickOnLoginButton();
        enterCredentionals();
        submitLoginButton();
    }

    public void clickOnLoginButton() {
//        driver.findElement(By.cssSelector("a.s-header-item__link.s-header-item__link--login")).click();
        driver.findElement(By.xpath("//a[contains(@href, 'https://www.livejournal.com/login.bml?returnto=https%3A%2F%2Fwww.livejournal.com%2F&ret=1')]")).click();
    }

    public void enterCredentionals() {
        String username = "elinbek";
        String password = "ANILE4002lj$";
        UserData user = new UserData(username, password);
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
        Assert.assertTrue(isElementPresent(By.cssSelector("a.s-header-item__link.s-header-item__link--friends.s-nav-rootlink-feed")));
        Assert.assertTrue(isElementPresent(By.cssSelector("a.s-header-item__link.s-header-item__link--user")));
    }
}
