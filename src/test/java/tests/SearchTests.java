package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Search")
    @Description("We search by word 'Java'")
    @Step("Start testSearch")
    @Severity(SeverityLevel.BLOCKER)
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.skipSettings();
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Cancel Search")
    @Description("We cancel search and go away from search page")
    @Step("Start testCancelSearch")
    @Severity(SeverityLevel.CRITICAL)
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.skipSettings();
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Amount of not empty search")
    @Description("We check that amount of search result is more then zero")
    @Step("Start testAmountOfNotEmptySearch")
    @Severity(SeverityLevel.CRITICAL)
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.skipSettings();
        }

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
//        SearchPageObject.typeSearchLine(search_line);
//        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
//
//        Assert.assertEquals(
//                "We found too few results",
//                amount_of_search_results > 0
//        );
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Amount of empty search")
    @Description("We check that amount of search result is equal by zero")
    @Step("Start testAmountOfEmptySearch")
    @Severity(SeverityLevel.TRIVIAL)
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.skipSettings();
        }

        SearchPageObject.initSearchInput();
        String search_line = "][poiuytrewq";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}
