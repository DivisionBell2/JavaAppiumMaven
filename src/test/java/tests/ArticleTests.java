package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.skipSettings();
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement("Object-oriented programming language");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");

        assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testSwipeArticleTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.skipSettings();
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticle();

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement("Appium");
        ArticlePageObject.swipeToFooter();
    }
}
