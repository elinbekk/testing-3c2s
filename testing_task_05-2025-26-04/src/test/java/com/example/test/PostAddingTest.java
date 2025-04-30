package com.example.test;

import com.example.model.PostData;
import com.example.model.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PostAddingTest extends TestBase {
    @BeforeAll
    public void doLogin() {
        UserData userData = new UserData("elinbek", "ANILE4002lj$");
        appManager.getLoginHelper().login(userData);
    }

    @MethodSource("postProvider")
    @ParameterizedTest
    public void postAddingIsSuccessful(PostData post)  {
        appManager.getPostHelper().navigateToPostCreation();
        appManager.getPostHelper().fillPostTitle(post.getTitle());
        appManager.getPostHelper().fillPostContent(post.getContent());
        appManager.getPostHelper().publishPost();
        appManager.getPostHelper().verifyPostPublishedSuccessfully(post.getTitle(), post.getContent());
    }

    private static List<PostData> postProvider() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
                new File("/home/elina/testing_3c2s/testing_task_04-2025-19-04/posts.json"),
                mapper.getTypeFactory().constructCollectionType(List.class, PostData.class)
        );
    }
}