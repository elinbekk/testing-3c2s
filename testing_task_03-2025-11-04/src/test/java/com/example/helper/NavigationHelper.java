package com.example.helper;

import com.example.ApplicationManager;

public class NavigationHelper extends HelperBase {
    private String baseUrl;
    public NavigationHelper(ApplicationManager appManager, String baseUrl) {
        super(appManager);
        this.baseUrl = baseUrl;
    }
    public void openMainPage() {
        driver.get("https://www.livejournal.com/");
    }
    public void openPostPage() {
        String URL = "https://www.livejournal.com/post";
        driver.get(URL);
    }
}
