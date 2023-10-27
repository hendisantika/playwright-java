package com.hendisantika;

import org.testng.annotations.Test;

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
}
