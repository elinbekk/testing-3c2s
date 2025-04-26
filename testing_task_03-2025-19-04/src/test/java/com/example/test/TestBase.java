package com.example.test;

import com.example.ApplicationManager;
import org.junit.Before;

public class TestBase {
    protected ApplicationManager appManager;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        appManager = ApplicationManager.getInstance();
    }
}
