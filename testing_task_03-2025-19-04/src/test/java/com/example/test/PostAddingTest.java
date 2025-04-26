package com.example.test;

import com.example.model.PostData;
import org.junit.Before;
import org.junit.Test;

public class PostAddingTest extends TestBase {
    @Before
    @Override
    public void setUp() {
        super.setUp();
//        appManager.getLoginHelper().login();
    }

    @Test
    public void postAddingIsSuccessful() {
        PostData postData = new PostData("title", "content");
        appManager.getPostHelper().navigateToPostCreation();
        appManager.getPostHelper().fillPostTitle(postData.getTitle());
        appManager.getPostHelper().fillPostContent(postData.getContent());
        appManager.getPostHelper().publishPost();
        appManager.getNavigationHelper().openPostPage();
        appManager.getPostHelper().verifyPostPublishedSuccessfully(postData.getTitle(), postData.getContent());
    }

    /*@Test
    public void postAddingFailureCase() {
        appManager.getPostHelper().navigateToPostCreation();
        appManager.getPostHelper().attemptToPublishEmptyPost();
        appManager.getDriver().findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Виден всем'])[1]/following::span[1]")).click();
        appManager.getPostHelper().verifyEmptyPostErrorDisplayed();
    }*/
}