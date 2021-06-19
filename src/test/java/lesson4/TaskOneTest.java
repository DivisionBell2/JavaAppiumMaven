package lesson4;

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

    //Адаптировать под MW тест на удаление одной сохраненной статьи из двух. Вместо проверки
    // title-элемента придумать другой способ верификации оставшейся статьи (т.е. способ убедиться, что осталась в
    // сохраненных ожидаемая статья).
    @Test
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
