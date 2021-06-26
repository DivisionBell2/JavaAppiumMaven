package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.mobile_web.MWMyListPageObject;
import lib.ui.mobile_web.MWNavigationUI;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {
    private static final String
    login = "neglavmag",
    password = "cnfylfhn12Ysq";

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "Authorization"), @Feature(value = "Navigation"), @Feature(value = "My lists")})
    @DisplayName("Save first article to my list")
    @Description("We save article in bookmark list and check it")
    @Step("Start testSaveFirstArticleToMyList")
    @Severity(SeverityLevel.CRITICAL)
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.skipSettings();
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement("Object-oriented programming language");
        String article_title = "Java (programming language)";
        String name_of_folder = "Learning programming";

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);

            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement("Object-oriented programming language");

            Assert.assertEquals(
                    "We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle("Object-oriented programming language")
            );

            MWNavigationUI NavigationUI = new MWNavigationUI(driver);

            NavigationUI.openNavigation();
            NavigationUI.clickMyLists();

            MWMyListPageObject MWMyListPageObject = new MWMyListPageObject(driver);

            MWMyListPageObject.swipeByArticleToDelete("programming language");
        }

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.clickCancelSearch();
            SearchPageObject.navigateToHomePage();

            NavigationUI NavigationUI = NavigationUIFactory.get(driver);

            NavigationUI.openNavigation();
            NavigationUI.clickMyLists();

            MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

            if (Platform.getInstance().isAndroid()) {
                MyListPageObject.openFolderByName(name_of_folder);
            }

            MyListPageObject.swipeByArticleToDelete(article_title);
            MyListPageObject.waitForNoArticles();
        }
    }
}
