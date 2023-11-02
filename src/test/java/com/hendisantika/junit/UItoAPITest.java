package com.hendisantika.junit;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by IntelliJ IDEA.
 * Project : playwright-java
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/27/23
 * Time: 16:18
 * To change this template use File | Settings | File Templates.
 */
public class UItoAPITest {
    @Test
    public void runSimpleAPI() {
        // create playwright and browser instances
        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions setHeadless = new BrowserType.LaunchOptions().setHeadless(false);
        Browser browser = playwright.chromium().launch(setHeadless);
        BrowserContext browserContext = browser.newContext();
        Page page = browserContext.newPage();

        //navigate to application
        page.navigate("https://www.jakmall.com/login");

        //run the login automation
        page.getByLabel("Email").fill("hendisantika@yahoo.co.id");
        page.getByLabel("Password").fill("Naruto2023!");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Login")).last().click();

        APIResponse response = browserContext.request().get("https://www.jakmall.com/cart/header",
                RequestOptions.create()
                        .setHeader("x-requested-with", "XMLHttpRequest")
        );

        System.out.println("======================");
        System.out.println(response.text());
        System.out.println("Ada lho ...!");
        System.out.println("======================");

        //close browsers and playwright instances
        Path screenshotPath = Paths.get("TEST CASE 3_" + System.currentTimeMillis() + ".jpg");
        page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));

// Start tracing before creating / navigating a page.
        browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        page.navigate("https://playwright.dev");

// Stop tracing and export it into a zip archive.
        browserContext.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));

        playwright.close();

    }
}
