package com.hendisantika.junit;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
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
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
public class SimplePlaywrightTest {
    @Test
    public void runSimplePlaywrightTest() {
        // create playwright and browser instances
        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions setHeadless = new BrowserType.LaunchOptions().setHeadless(false);
        Page page = playwright.chromium().launch(setHeadless).newPage();

        //navigate to application
        page.navigate("https://bookcart.azurewebsites.net/");

        //run the automation
        page.getByText("Login").click();
        page.getByLabel("Username").fill("ortoni");
        page.getByLabel("Password").fill("Pass1234$");
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
}
