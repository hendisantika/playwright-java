package com.hendisantika;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * Project : playwright-java
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/27/23
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public class SearchTests extends BaseTests {

    @Test
    public void searchForExactTitle() {
        String title = "Agile Testing";
        searchPage.search(title);
        assertEquals(searchPage.getNumberOfVisibleBooks(), 1, "Number of visible books");
        assertTrue(searchPage.getVisibleBooks().contains(title), "Title of visible book");
    }

    @Test
    public void searchForPartialTitle() {
        searchPage.search("Test");

        List<String> expectedBooks = List.of(
                "Test Automation in the Real World",
                "Experiences of Test Automation",
                "Agile Testing",
                "How Google Tests Software",
                "Java For Testers"
        );

        assertEquals(searchPage.getNumberOfVisibleBooks(), expectedBooks.size(), "Number of visible books");
        assertEquals(searchPage.getVisibleBooks(), expectedBooks, "Titles of visible books");
    }
}
