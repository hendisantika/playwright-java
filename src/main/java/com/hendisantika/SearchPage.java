package com.hendisantika;

import com.microsoft.playwright.Page;

/**
 * Created by IntelliJ IDEA.
 * Project : playwright-java
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/27/23
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
public class SearchPage {
    private final Page page;

    private final String locator_searchBar = "#searchBar";
    private final String locator_hiddenBooks = "li.ui-screen-hidden";
    private final String locator_visibleBooks = "li:not(.ui-screen-hidden)";
    private final String locator_visibleBookTitles = "li:not(.ui-screen-hidden) h2";

    public SearchPage(Page page) {
        this.page = page;
    }
}
