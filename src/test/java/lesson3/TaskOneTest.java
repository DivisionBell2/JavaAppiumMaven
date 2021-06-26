package lesson3;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class TaskOneTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Check text")
    @Description("We check that search input has text 'Search Wikipedia'")
    @Step("Start testSearchText")
    @Severity(SeverityLevel.TRIVIAL)
    public void testCheckText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipSettings();
        SearchPageObject.initSearchInput();

        Assert.assertEquals(
                "Search input has not expected text",
                "Search Wikipedia",
                SearchPageObject.getSearchInitElementText()
        );
    }
}
