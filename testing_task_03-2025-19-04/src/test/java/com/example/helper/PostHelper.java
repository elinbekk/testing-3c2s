package com.example.helper;

import com.example.ApplicationManager;
import com.example.model.PostData;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.net.URI;

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
        //	css=div.notranslate.public-DraftEditor-content
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
        PostData postData = getCreatedPostData();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(expectedTitle, postData.getTitle());
        Assert.assertEquals(expectedContent, postData.getContent());
    }

    public PostData getCreatedPostData() {
        String titleSelector = "h1.aentry-post__title span.aentry-post__title-text";
        String contentSelector = "div.aentry-post__text--view";
        String postTitle = driver.findElement(By.cssSelector(titleSelector)).getText();
        String postContent = driver.findElement(By.cssSelector(contentSelector)).getText();
        return new PostData(postTitle, postContent);
    }

    public int extractPostIdFromURL() {
        String path = URI.create(appManager.getDriver().getCurrentUrl()).getPath();
        return Integer.parseInt(path.split("/")[1].split("\\.html")[0]);
    }

    public void deletePost() {
        String deletePostText = "Удалить пост";
        appManager.getDriver().findElement(By.linkText(deletePostText)).click();
        appManager.getDriver().findElement(By.xpath("//*/text()[normalize-space(.)='Удалить']/parent::*")).click();
        appManager.getDriver().get("https://elinbek.livejournal.com/");
    }

    public void verifyPostDeletedSuccessfully(int postId) throws InterruptedException {
        sleep(3000);
        String postElementId = "entry-elinbek-" + postId;
        Assert.assertFalse(isElementPresent(By.id(postElementId)));

        Assert.assertFalse(isElementPresent(By.xpath("//article[@id=" + postElementId + "']/div[3]/div/p")));
    }

    /*public void verifyEmptyPostErrorDisplayed() {
        String errorMessageXpath = "//div[contains(text(), 'Нельзя опубликовать пустую запись')]";
        WebElement errorMessage = driver.findElement(
                By.xpath(errorMessageXpath)
        );
        Assert.assertTrue(errorMessage.isDisplayed());
    }*/

}
