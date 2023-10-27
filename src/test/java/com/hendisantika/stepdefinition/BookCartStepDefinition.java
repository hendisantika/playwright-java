package com.hendisantika.stepdefinition;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * Project : playwright-java
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/27/23
 * Time: 16:03
 * To change this template use File | Settings | File Templates.
 */
public class BookCartStepDefinition {
    private Page page;
    private Playwright playwright;

    @Given("user already logged in")
    public void userAlreadyLoggedIn() {
        //navigate to application
        page.navigate("https://bookcart.azurewebsites.net/");

        //run the automation
        page.getByText("Login").click();
        page.getByLabel("Username").fill("ortoni");
        page.getByLabel("Password").fill("Pass1234$");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Login")).last().click();
    }

    @When("user searches for a {string} book")
    public void userSearchesForABook(String bookTitle) throws InterruptedException {
        Thread.sleep(1000);
        page.getByPlaceholder("Search books", new Page.GetByPlaceholderOptions()
                .setExact(false)).type(bookTitle);
        page.getByRole(AriaRole.OPTION).first().click();
    }


    @Then("user find {string} on the search result")
    public void userFindOnTheSearchResult(String bookTitle) {
        assertTrue(
                page.locator("//app-book-card[.//strong[text()='" + bookTitle + "']]")
                        .isVisible()
        );
    }
}
