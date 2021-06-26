package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class CheckAppConditionTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Change screen orientation on search results")
    @Description("We open change screen orientation on search page for checking correct visibled results")
    @Step("Start testChangeScreenOrientationOnSearchResults")
    @Severity(SeverityLevel.MINOR)
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

        Assert.assertEquals(
                "Article title have been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();

        title_after_rotation = ArticlePageObject.getArticleTitle("Java (programming language)");
        Assert.assertEquals(
                "Article title have been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Check search article in background")
    @Description("We check searched article by description")
    @Step("Start testCheckSearchArticleInBackground")
    @Severity(SeverityLevel.MINOR)
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
