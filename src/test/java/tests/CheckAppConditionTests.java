package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class CheckAppConditionTests extends CoreTestCase {
    @Test
    public void testChangeScreenOrientationOnSearchResults() {

        if (Platform.getInstance().isMw()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipSettings();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement("Object-oriented programming language");
        String title_before_rotation = ArticlePageObject.getArticleTitle("Java (programming language)");
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle("Java (programming language)");

        assertEquals(
                "Article title have been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();

        title_after_rotation = ArticlePageObject.getArticleTitle("Java (programming language)");
        assertEquals(
                "Article title have been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground() {

        if (Platform.getInstance().isMw()) {
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipSettings();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
    }
}
