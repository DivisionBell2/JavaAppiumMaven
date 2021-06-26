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
