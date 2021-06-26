package lesson3;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class TaskTwoTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Check find articles and cancel search")
    @Description("We check articles in search results by word 'Java' and then clean search results")
    @Step("Start testCheckFindArticlesAndCancelSearch")
    @Severity(SeverityLevel.MINOR)
    public void testCheckFindArticlesAndCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipSettings();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForArticleListIsEmpty();
    }
}
