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


    private void navigateToPostCreation() {
        String buttonSelector = "a.b-flatbutton.b-flatbutton-simple[href='https://www.livejournal.com/post']";
        driver.findElement(By.cssSelector(buttonSelector)).click();
    }

    private void openPostPage() {
        String URL = "https://www.livejournal.com/post";
        driver.get(URL);
    }

    private void fillPostTitle(String title) {
        String textareaXpath = "//div[@id='content']/div/div/div[2]/textarea";
        driver.findElement(By.xpath(textareaXpath)).click();
        driver.findElement(By.xpath(textareaXpath)).clear();
        driver.findElement(By.xpath(textareaXpath)).sendKeys(title);
    }

    private void fillPostContent(String content) {
        String selector = "div.DraftEditor-root div.DraftEditor-editorContainer div.public-DraftEditor-content";
        driver.findElement(By.cssSelector(selector)).click();
        driver.findElement(By.cssSelector(selector)).sendKeys(content);
    }

    private void publishPost() {
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Виден всем'])[1]/following::span[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Расширенные настройки'])[1]/following::span[1]")).click();
    }

    private void attemptToPublishEmptyPost() {
        String contentSelector = "div.DraftEditor-root div.DraftEditor-editorContainer div.public-DraftEditor-content";
        String titleSelector = "//div[@id='content']/div/div/div[2]/textarea";
        driver.findElement(By.xpath(titleSelector)).click();
        driver.findElement(By.cssSelector(contentSelector)).click();
    }

    private void verifyPostPublishedSuccessfully(String expectedTitle, String expectedContent) {
        String titleSelector = "h1.aentry-post__title span.aentry-post__title-text";
        String contentSelector = "div.aentry-post__text--view";
        By postTitle = By.cssSelector(titleSelector);
        Assert.assertEquals(expectedTitle, driver.findElement(postTitle).getText());
        Assert.assertTrue(driver.findElement(By.cssSelector(contentSelector)).getText().contains(expectedContent));
    }
    private void verifyEmptyPostErrorDisplayed() {
        String errorMessageXpath = "//div[contains(text(), 'Нельзя опубликовать пустую запись')]";
        WebElement errorMessage = driver.findElement(
                By.xpath(errorMessageXpath)
        );
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    private void openMainPage() {
        driver.get("https://www.livejournal.com/");
    }

    private void clickOnLoginButton() {
        String loginXpath = "//a[contains(@href, 'https://www.livejournal.com/login.bml?returnto=https%3A%2F%2Fwww.livejournal.com%2F&ret=1')]";
//        driver.findElement(By.cssSelector("a.s-header-item__link.s-header-item__link--login")).click();
        driver.findElement(By.xpath(loginXpath)).click();
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


