package com.hendisantika;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Created by IntelliJ IDEA.
 * Project : playwright-java
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/27/23
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */
public class BaseTests {
    protected SearchPage searchPage;
    private Browser browser;

    @BeforeClass
    public void setUp() {

        //Open a browser (supports Chromium (Chrome, Edge), Firefox, and Webkit (Safari))
        browser = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));

        //A single browser tab
        Page page = browser.newPage();
        page.navigate("https://automationbookstore.dev/");
        searchPage = new SearchPage(page);
    }

    @AfterClass
    public void tearDown() {
        browser.close();
    }
}
