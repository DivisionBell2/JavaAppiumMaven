package lesson4;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class TaskOneTest extends CoreTestCase {

    //Адаптировать под iOS тест на удаление одной сохраненной статьи из двух.
    // Вместо проверки title-элемента придумать другой способ верификации оставшейся статьи
    // (т.е. способ убедиться, что осталась в сохраненных ожидаемая статья).
    @Test
    public void testSaveFirstArticleToMyList() {
        String firstSearchLine = "Java";
        String secondSearchLine = "Python";
        String name_of_folder = "Learning programming";
        String first_article_substring = "Object-oriented programming language";
        String first_article_title = "Java (programming language)";
        String second_article_title = "Python (programming language)";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipSettings();
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

        ArticlePageObject.closeArticle();
        SearchPageObject.clickCancelSearch();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(secondSearchLine);
        SearchPageObject.clickByArticleWithSubstring(second_article_title);

        ArticlePageObject.waitForTitleElement(second_article_title);
        ArticlePageObject.addArticleToExistedList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.clickCancelSearch();
        SearchPageObject.navigateToHomePage();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(name_of_folder);
            MyListPageObject.swipeByArticleToDelete(first_article_title);
        }


        MyListPageObject.clickToSavedArticleByName(second_article_title);

        ArticlePageObject.waitForTitleElement(second_article_title);
    }
}
