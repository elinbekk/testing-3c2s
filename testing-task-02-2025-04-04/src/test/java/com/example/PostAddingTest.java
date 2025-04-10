package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PostAddingTest extends TestBase {

    @Override
    public void setUp() {
        super.setUp();
        openMainPage();
        clickOnLoginButton();
        enterCredentionals();
        submitLoginButton();
    }

    @Test
    public void postAddingSuccessCase() {
        PostData postData = new PostData("title", "content");
        navigateToPostCreation();
        fillPostContent(postData.getTitle());
        fillPostTitle(postData.getContent());
        publishPost();
        openPostPage();
        verifyPostPublishedSuccessfully(postData.getTitle(), postData.getContent());
    }

    @Test
    public void postAddingFailureCase(){
        navigateToPostCreation();
        attemptToPublishEmptyPost();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Виден всем'])[1]/following::span[1]")).click();
        verifyEmptyPostErrorDisplayed();
    }

    private void openPostPage() {
        driver.get("https://elinbek.livejournal.com/1519.html?newpost=1");
    }

    private void navigateToPostCreation() {
        driver.findElement(By.cssSelector("a.b-flatbutton.b-flatbutton-simple[href='https://www.livejournal.com/post']")).click();
    }

    private void fillPostTitle(String title) {
        driver.findElement(By.xpath("//div[@id='content']/div/div/div[2]/textarea")).click();
        driver.findElement(By.xpath("//div[@id='content']/div/div/div[2]/textarea")).clear();
        driver.findElement(By.xpath("//div[@id='content']/div/div/div[2]/textarea")).sendKeys(title);
    }

    private void fillPostContent(String content) {
        driver.findElement(By.cssSelector("div.DraftEditor-root div.DraftEditor-editorContainer div.public-DraftEditor-content")).click();
        driver.findElement(By.cssSelector("div.DraftEditor-root div.DraftEditor-editorContainer div.public-DraftEditor-content")).sendKeys(content);
    }

    private void publishPost() {
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Виден всем'])[1]/following::span[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Расширенные настройки'])[1]/following::span[1]")).click();
    }

    private void attemptToPublishEmptyPost() {
        driver.findElement(By.xpath("//div[@id='content']/div/div/div[2]/textarea")).click();
        driver.findElement(By.cssSelector("div.DraftEditor-root div.DraftEditor-editorContainer div.public-DraftEditor-content")).click();
    }

    private void verifyPostPublishedSuccessfully(String expectedTitle, String expectedContent) {
        By postTitle = By.cssSelector("h1.aentry-post__title span.aentry-post__title-text");
        Assert.assertEquals("title", driver.findElement(postTitle).getText());
        Assert.assertTrue(driver.findElement(By.cssSelector("div.aentry-post__text--view")).getText().contains("content"));
    }
    private void verifyEmptyPostErrorDisplayed() {
        WebElement errorMessage = driver.findElement(
                By.xpath("//div[contains(text(), 'Нельзя опубликовать пустую запись')]")
        );
        Assert.assertTrue(errorMessage.isDisplayed());
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
}


