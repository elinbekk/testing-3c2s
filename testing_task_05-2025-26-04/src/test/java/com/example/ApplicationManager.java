package com.example;

import com.example.helper.LoginHelper;
import com.example.helper.NavigationHelper;
import com.example.helper.PostHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.fail;

public class ApplicationManager {
    private final WebDriver driver;
    private final String baseUrl;
    private final NavigationHelper navigationHelper;
    private final LoginHelper loginHelper;
    private final PostHelper postHelper;
    private final JavascriptExecutor js;
    protected final StringBuffer verificationErrors = new StringBuffer();

    private static ThreadLocal<ApplicationManager> app = new ThreadLocal<>();

    private ApplicationManager() {
        this.driver = new ChromeDriver();
        this.baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        this.js = (JavascriptExecutor) driver;

        this.navigationHelper = new NavigationHelper(this, baseUrl);
        this.loginHelper = new LoginHelper(this);
        this.postHelper = new PostHelper(this);

//        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));

    }

    public void stop(){
        if(driver != null){
            driver.quit();
        }
        String verificationErrorString = verificationErrors.toString();
        if (!verificationErrorString.isEmpty()) {
            fail(verificationErrorString);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public LoginHelper getLoginHelper() {
        return loginHelper;
    }

    public PostHelper getPostHelper() {
        return postHelper;
    }

    public static ApplicationManager getInstance() {
        if(app == null || app.get() == null) {
            ApplicationManager newInstance = new ApplicationManager();
            newInstance.getNavigationHelper().openMainPage();
            app.set(newInstance);
        }
        return app.get();
    }
}
