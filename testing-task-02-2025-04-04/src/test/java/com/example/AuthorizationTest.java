package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;

import static org.junit.Assert.fail;

public class AuthorizationTest extends TestBase {
    @Test
    public void testUntitledTestCase() {
        openMainPage();
        clickOnLoginButton();
        enterCredentionals();
        submitLoginButton();
        checkUserIsLoggedIn();
    }

    private void openMainPage() {
        driver.get("https://www.livejournal.com/");
    }

    private void clickOnLoginButton() {
//        driver.findElement(By.cssSelector("a.s-header-item__link.s-header-item__link--login")).click();
        driver.findElement(By.xpath("//a[contains(@href, 'https://www.livejournal.com/login.bml?returnto=https%3A%2F%2Fwww.livejournal.com%2F&ret=1')]")).click();
    }

    private void enterCredentionals() {
        String username = "elinbek";
        String password = "ANILE4002lj$";
        UserData user = new UserData(username, password);
        driver.findElement(By.id("user")).clear();
        driver.findElement(By.id("user")).sendKeys(user.getUsername());
        driver.findElement(By.id("lj_loginwidget_password")).click();
        driver.findElement(By.id("lj_loginwidget_password")).clear();
        driver.findElement(By.id("lj_loginwidget_password")).sendKeys(user.getPassword());
    }

    private void submitLoginButton() {
        driver.findElement(By.name("action:login")).click();
    }

    private void checkUserIsLoggedIn() {
        Assert.assertTrue(isElementPresent(By.cssSelector("a.s-header-item__link.s-header-item__link--friends.s-nav-rootlink-feed")));
        Assert.assertTrue(isElementPresent(By.cssSelector("a.s-header-item__link.s-header-item__link--user")));
    }
}