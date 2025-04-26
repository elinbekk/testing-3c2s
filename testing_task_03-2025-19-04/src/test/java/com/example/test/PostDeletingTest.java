package com.example.test;

import com.example.model.PostData;
import org.junit.Before;
import org.junit.Test;

public class PostDeletingTest extends TestBase {
    @Before
    @Override
    public void setUp() {
        super.setUp();
//        appManager.getLoginHelper().login();
    }

    @Test
    public void deletePostTest() throws InterruptedException {
        int postId = createPost();
        appManager.getNavigationHelper().navigateToPostEditing();
        appManager.getPostHelper().deletePost();
        appManager.getPostHelper().verifyPostDeletedSuccessfully(postId);
    }

    public int createPost() {
        PostData postData = new PostData("title", "content");
        appManager.getPostHelper().navigateToPostCreation();
        appManager.getPostHelper().fillPostTitle(postData.getTitle());
        appManager.getPostHelper().fillPostContent(postData.getContent());
        appManager.getPostHelper().publishPost();
        appManager.getNavigationHelper().openPostPage();
        int postId = appManager.getPostHelper().extractPostIdFromURL();
        return postId;
    }
}
