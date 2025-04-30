package com.example.test;

import com.example.ApplicationManager;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBase {
    protected ApplicationManager appManager;

    @BeforeAll
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        appManager = ApplicationManager.getInstance();
    }

    @AfterAll
    public void tearDown() {
        appManager.stop();
    }
}
