package com.example.test;

import com.example.model.UserData;
import org.junit.Test;

public class AuthorizationTest extends TestBase {
    @Test
    public void authorizationIsSuccessful() {
        UserData userData = new UserData("ELINBEK", "ANILE4002lj$");
        appManager.getLoginHelper().login(userData);
        appManager.getLoginHelper().checkUserIsLoggedIn();
    }
}