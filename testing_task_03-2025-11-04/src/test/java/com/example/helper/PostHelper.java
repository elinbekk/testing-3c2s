package com.example.helper;

import com.example.ApplicationManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class PostHelper extends HelperBase {
    public PostHelper(ApplicationManager appManager) {
        super(appManager);
    }

    public void navigateToPostCreation() {
        String buttonSelector = "a.b-flatbutton.b-flatbutton-simple[href='https://www.livejournal.com/post']";
        driver.findElement(By.cssSelector(buttonSelector)).click();
    }

    public void fillPostTitle(String title) {
        String textareaXpath = "//div[@id='content']/div/div/div[2]/textarea";
        driver.findElement(By.xpath(textareaXpath)).click();
        driver.findElement(By.xpath(textareaXpath)).clear();
        driver.findElement(By.xpath(textareaXpath)).sendKeys(title);
    }

    public void fillPostContent(String content) {
        String selector = "div.DraftEditor-root div.DraftEditor-editorContainer div.public-DraftEditor-content";
        driver.findElement(By.cssSelector(selector)).click();
        driver.findElement(By.cssSelector(selector)).sendKeys(content);
    }

    public void publishPost() {
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Виден всем'])[1]/following::span[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Расширенные настройки'])[1]/following::span[1]")).click();
    }

    public void attemptToPublishEmptyPost() {
        String contentSelector = "div.DraftEditor-root div.DraftEditor-editorContainer div.public-DraftEditor-content";
        String titleSelector = "//div[@id='content']/div/div/div[2]/textarea";
        driver.findElement(By.xpath(titleSelector)).click();
        driver.findElement(By.cssSelector(contentSelector)).click();
    }

    public void verifyPostPublishedSuccessfully(String expectedTitle, String expectedContent) {
        String titleSelector = "h1.aentry-post__title span.aentry-post__title-text";
        String contentSelector = "div.aentry-post__text--view";
        By postTitle = By.cssSelector(titleSelector);

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(expectedTitle, driver.findElement(postTitle).getText());
        Assert.assertTrue(driver.findElement(By.cssSelector(contentSelector)).getText().contains(expectedContent));
    }

    public void verifyEmptyPostErrorDisplayed() {
        String errorMessageXpath = "//div[contains(text(), 'Нельзя опубликовать пустую запись')]";
        WebElement errorMessage = driver.findElement(
                By.xpath(errorMessageXpath)
        );
        Assert.assertTrue(errorMessage.isDisplayed());
    }

}
