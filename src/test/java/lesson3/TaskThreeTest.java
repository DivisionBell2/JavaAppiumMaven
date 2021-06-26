package lesson3;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TaskThreeTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Check search has expected word")
    @Description("We check that all search results have expected word")
    @Step("Start CheckSearchHasExpectedWord")
    @Severity(SeverityLevel.NORMAL)
    public void testCheckSearchHasExpectedWord() {
        String keyWord = "Java";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipSettings();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(keyWord);

        List<String> articlesTitles = SearchPageObject.getArticleListBySearch();

        for (String articleTitle : articlesTitles) {
            Assert.assertTrue(articleTitle.toLowerCase().contains(keyWord.toLowerCase()));
        }
    }
}
