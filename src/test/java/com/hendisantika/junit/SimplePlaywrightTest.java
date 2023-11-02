package com.hendisantika.junit;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project : playwright-java
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/27/23
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
public class SimplePlaywrightTest {
    @Test
    @DisplayName("Test Case 1 - Tentang Login")
//    public void runSimplePlaywrightTest() {
    public void loginPositiveTest() {
        // create playwright and browser instances
        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions setHeadless = new BrowserType.LaunchOptions().setHeadless(false);
        Page page = playwright.chromium().launch(setHeadless).newPage();

//        Browser browser = playwright.chromium().launch(setHeadless);
//        BrowserContext browserContext = browser.newContext();

        //navigate to application
        page.navigate("https://bookcart.azurewebsites.net/");

        //run the automation
        page.getByText("Login").click();
        page.getByLabel("Username").fill("yuji");
        page.getByLabel("Password").fill("Yuji2023!");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Login")).last().click();
        page.getByPlaceholder("Search books", new Page.GetByPlaceholderOptions()
                .setExact(false)).type("The Hookup");
        page.getByRole(AriaRole.OPTION).first().click();
        page.getByAltText("Book cover image").click();

        //get final screenshot
        Path screenshotPath = Paths.get(System.currentTimeMillis() + ".jpg");
        page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));

        //close browsers and playwright instances
        playwright.close();
    }

    @Test
    @DisplayName("Test Case 2 - Add to Book Cart")
    public void add2BookCart() {
        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions setHeadLess = new BrowserType.LaunchOptions().setHeadless(false);
        Page page = playwright.chromium().launch(setHeadLess).newPage();

        page.navigate("https://bookcart.azurewebsites.net/");
        System.out.println("======================");
//        assertThat(page.locator("body > app-root > div > app-home > div > div.col.mb-3 > div > div:nth-child(3) > app-book-card > mat-card > mat-card-content:nth-child(2) > div > a > strong")).containsText("HP4");
        assertThat(page.locator
                ("body > app-root > div > app-home > div > div.col.mb-3 > " +
                        "div > div:nth-child(3) > app-book-card > mat-card > " +
                        "mat-card-content:nth-child(2) > " +
                        "div > a > strong")).containsText("HP4");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Add to Cart")).last().click();

        assertThat(page.getByText("One Item added to cart")).isVisible();
        System.out.println("Ada lho ...!");
        System.out.println("======================");

        Path screenshotPath = Paths.get("TEST CASE 2_" + System.currentTimeMillis() + ".jpg");
        page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));
        playwright.close();
//        Browser browser = playwright.chromium().launch(setHeadLess);
//        BrowserContext browserContext = browser.newContext();
//        // Start tracing before creating / navigating a page.
//        browserContext.tracing().start(new Tracing.StartOptions()
//                .setScreenshots(true)
//                .setSnapshots(true)
//                .setSources(true));
//
//        page.navigate("https://playwright.dev");
//
//// Stop tracing and export it into a zip archive.
//        browserContext.tracing().stop(new Tracing.StopOptions()
//                .setPath(Paths.get("trace.zip")));
//        playwright.close();
    }
}
