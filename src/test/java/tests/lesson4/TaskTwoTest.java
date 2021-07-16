package tests.lesson4;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class TaskTwoTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Check present article title positive")
    @Description("We check that article has name relevanted search word 'Java'")
    @Step("Start testCheckPresentArticleTitlePositive")
    @Severity(SeverityLevel.NORMAL)
    public void testCheckPresentArticleTitlePositive() {
        String searchLine = "Java";
        String articleSubstring = "Object-oriented programming language";
        String articleTitle = "Java (programming language)";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipSettings();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.clickByArticleWithSubstring(articleSubstring);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement(articleTitle);
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Check present article title positive")
    @Description("We check that article has name relevanted search word 'Java'")
    @Step("Start testCheckPresentArticleTitleNegative")
    @Severity(SeverityLevel.NORMAL)
    public void testCheckPresentArticleTitleNegativeResult() {
        String searchLine = "Java";
        String articleSubstring = "Object-oriented programming language";
        String articleTitle = "Java (programming language)";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipSettings();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.clickByArticleWithSubstring(articleSubstring);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElementWithoutWaiting(articleTitle);
    }
}
