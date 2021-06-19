package lesson3;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class TaskTwoTest extends CoreTestCase {

    @Test
    public void testCheckFindArticlesAndCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipSettings();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForArticleListIsEmpty();
    }
}
