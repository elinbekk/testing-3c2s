package com.example.test;

import com.example.model.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class AuthorizationTest extends TestBase {
    /*@Test
    public void authorizationIsSuccessful() {
        appManager.getLoginHelper().login();
        appManager.getLoginHelper().checkUserIsLoggedIn();
    }*/
    @ParameterizedTest
    @MethodSource("userProvider")
    public void authorizationIsSuccessful(UserData user) {
            appManager.getLoginHelper().login(user);
            appManager.getLoginHelper().checkUserIsLoggedIn();
    }

    private static Stream<UserData> userProvider() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<UserData> users = mapper.readValue(
                new File("/home/elina/testing_3c2s/testing_task_03-2025-19-04/users.json"),
                mapper.getTypeFactory().constructCollectionType(List.class, UserData.class)
        );
        return users.stream();
    }
}