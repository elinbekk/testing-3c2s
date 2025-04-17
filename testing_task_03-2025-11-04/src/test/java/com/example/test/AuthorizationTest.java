package com.example.test;

import org.junit.Test;

public class AuthorizationTest extends TestBase {
    @Test
    public void authorizationIsSuccessful() {
        appManager.getLoginHelper().login();
        appManager.getLoginHelper().checkUserIsLoggedIn();
    }
}