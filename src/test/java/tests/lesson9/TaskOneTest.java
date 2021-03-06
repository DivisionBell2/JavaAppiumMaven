package tests.lesson9;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.mobile_web.MWNavigationUI;
import org.junit.Test;

public class TaskOneTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "Authorization"), @Feature(value = "Navigation"), @Feature(value = "My lists")})
    @DisplayName("Save first article to my list")
    @Description("We save two articles in my list, then delete first article and check that second article exists in list")
    @Step("Start testSaveFirstArticleToMyList")
    @Severity(SeverityLevel.NORMAL)
    public void testSaveFirstArticleToMyList() {
        String firstSearchLine = "Java";
        String secondSearchLine = "Python";
        String name_of_folder = "Learning programming";
        String first_article_substring = "Object-oriented programming language";
        String first_article_title = "Java (programming language)";
        String second_article_title = "Python (programming language)";
        String login = "neglavmag";
        String password = "cnfylfhn12Ysq";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.skipSettings();
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(firstSearchLine);
        SearchPageObject.clickByArticleWithSubstring(first_article_substring);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement(first_article_title);

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);

            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement("first_article_substring");
        }

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
            SearchPageObject.clickCancelSearch();
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(secondSearchLine);
        SearchPageObject.clickByArticleWithSubstring(second_article_title);

        ArticlePageObject.waitForTitleElement(second_article_title);

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToExistedList(name_of_folder);
            ArticlePageObject.closeArticle();

            SearchPageObject.clickCancelSearch();
            SearchPageObject.navigateToHomePage();

            NavigationUI NavigationUI = NavigationUIFactory.get(driver);

            NavigationUI.clickMyLists();

            MyListPageObject.openFolderByName(name_of_folder);
            MyListPageObject.swipeByArticleToDelete(first_article_title);
            MyListPageObject.clickToSavedArticleByName(second_article_title);

            ArticlePageObject.waitForTitleElement(second_article_title);
        }

        if (Platform.getInstance().isMw()) {
            ArticlePageObject.addArticlesToMySaved();
            MWNavigationUI NavigationUI = new MWNavigationUI(driver);

            NavigationUI.openNavigation();
            NavigationUI.clickMyLists();

            MyListsPageObject mwMyListPageObject = MyListsPageObjectFactory.get(driver);

            mwMyListPageObject.swipeByArticleToDelete(first_article_title);
            mwMyListPageObject.clickToSavedArticleByName(second_article_title);
            ArticlePageObject.waitForTitleElement(second_article_title);
        }
    }
}
