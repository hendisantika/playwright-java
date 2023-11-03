package com.hendisantika.junit;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.RecordVideoSize;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    //  a path object that represents the folder where you want to save the videos.  "videos" folder  is located in the current working directory.
    String workingDirectory = System.getProperty("user.dir");
    // location to store the videos.
    Path executionVideosFolder = Paths.get(workingDirectory, "videos");
    //Convert it to String path.
    String path = executionVideosFolder.toString();

    @Test
    @DisplayName("Test Case 1 - Tentang Login")
//    public void runSimplePlaywrightTest() {
    public void loginPositiveTest() {
        // create playwright and browser instances
        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions setHeadless = new BrowserType.LaunchOptions().setHeadless(false);
        Page page = playwright.chromium().launch(setHeadless).newPage();

        Browser browser = playwright.chromium().launch(setHeadless);
//        BrowserContext browserContext = browser.newContext();

        //navigate to application
        page.navigate("https://bookcart.azurewebsites.net/");

        //run the automation
        page.getByText("Login").click();
        page.getByLabel("Username").fill("yuji");
        page.getByLabel("Password").fill("Yuji2023!");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Login")).last().click();
        page.getByPlaceholder("Search books or authors", new Page.GetByPlaceholderOptions()
                .setExact(false)).type("The Hookup");
        page.getByRole(AriaRole.OPTION).first().click();
        page.getByAltText("Book cover image").click();

        //get final screenshot
        Path screenshotPath = Paths.get(System.currentTimeMillis() + ".jpg");
        page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));

        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("./videos/"))
                .setRecordVideoSize(640, 480));
        System.out.println("Path: " + Paths.get("./videos/"));

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

    @Test
    @DisplayName("Test Case 3 - Save Video Test")
    public void saveVideoTest() throws IOException {
        Playwright playwright = Playwright.create();
     /*
     The BrowserContext object represents a separate browsing context within the browser.
     This means that each BrowserContext object has its own set of cookies, cache, and other state.
     The newContextOptions() method allows you to specify options for the new browser context.
     In this case, you are specifying the following options:
      */

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // setRecordVideoDir(): This option specifies the folder where the videos should be saved.
        // setRecordVideoSize(): This option specifies the size of the videos that are recorded.
        // Unfortunately, there is no option to give file name
        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions().setRecordVideoDir(Paths.get(path))
                        .setRecordVideoSize(new RecordVideoSize(1280, 720))
        );

        System.out.println("Path: " + path);

        /*
        the difference between Page page = browser.newPage(); and Page page = context.newPage();.

        The Page object represents a single web page that can be navigated, interacted with, and tested.
        The browser.newPage() method creates a new Page object in the default browser context.
        The default browser context is the one that is created when you first start the playwright instance.

        The context.newPage() method creates a new Page object in the specified browser context.
        In your code, you are specifying the browser context that you created in the previous line of code.

        The reason why Page page = context.newPage(); should be used is that it allows you to record videos for each browser context.
        This is useful if you want to record videos of different tests that are running in different browser contexts.

        Here is a breakdown of the difference between the two lines of code:

        Page page = browser.newPage();
          Creates a new Page object in the default browser context.
          Does not record videos.

        Page page = context.newPage();
         Creates a new Page object in the specified browser context.
         Records videos for the specified browser context.
        so don't use Page page = browser.newPage();  and use  Page page = context.newPage();
         */
        Page page = context.newPage();
        //  This code instructs the page object to navigate to the specified URL
        page.navigate("https://bookcart.azurewebsites.net/");
        System.out.println("======================");
        System.out.println(page.title());
        assertThat(page).hasTitle("BookCart");
        //run the automation
        page.getByText("Login").click();
        page.getByLabel("Username").fill("yuji");
        page.getByLabel("Password").fill("Yuji2023!");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Login")).last().click();

        page.getByPlaceholder("Search books or authors", new Page.GetByPlaceholderOptions()
                .setExact(false)).type("The Martian");
        page.getByRole(AriaRole.OPTION).first().click();
        page.getByAltText("Book cover image").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Add to Cart")).last().click();

        assertThat(page.getByText("One Item added to cart")).isVisible();
        System.out.println("Ada lho ...!");
        System.out.println("======================");
        // Wait for the element to be available Wait are optional.
//        page.waitForSelector("//a[contains(@href, '/hovers')]");
        // Click the element
//        page.click("//a[contains(@href, '/hovers')]");
//        Locator myImage = page.locator("(//img[@alt='User Avatar'])[1]");
//        myImage.hover();
        // Wait for the element with the text to be available
//        page.waitForSelector("h5");
        // Retrieve the text of the element
//        String elementText = page.innerText("h5");
//        System.out.println("Text after hover: " + elementText);
        // Closes the currently open web page or browsing context. Terminates any ongoing network activity related to the page.
        page.close();
        //The next line of code closes the page object. This is important because if you do not close the page object, the video recording will continue even after the page is closed.
        //  This is necessary to stop the video recording.. close the video  after page closure.
        context.close();
        browser.close();
        playwright.close();

      /*
      There is a limitation in Playwright that file name can't  be given, so random value is generated.
      So, let's rename it.
       */
        // First get the current video file name that generated.
        String originalVideoPath = page.video().path().toString();
        System.out.println("generated video path is-" + originalVideoPath);

        // I am planning to get the class name of the current class without the package name and rename the file name with it.
        String className = getClass().getSimpleName();
        // Create a new file object for the new video file
        File renameVideoFile = new File(executionVideosFolder.toString(), className + "_" + System.currentTimeMillis() + ".webm");
        // Rename the old video file to the new file name, here I give the class name.
        Files.move(Paths.get(originalVideoPath), renameVideoFile.toPath());
        System.out.println("Renamed  video's new path is-" + renameVideoFile);
    }
}
