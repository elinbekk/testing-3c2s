package com.example.helper;

import com.example.ApplicationManager;
import org.openqa.selenium.By;

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

    public void navigateToPostEditing(){
        driver.findElement(By.cssSelector("svg.svgicon.svgicon.svgicon--more")).click();
        driver.findElement(By.xpath("//*/text()[normalize-space(.)='Редактировать запись']/parent::*")).click();
    }
}
