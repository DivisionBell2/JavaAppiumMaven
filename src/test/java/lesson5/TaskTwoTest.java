package lesson5;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import java.util.List;

public class TaskTwoTest extends CoreTestCase {

    // Адаптировать по iOS тест на поиск и верификацию трех результатов выдачи поиска.
    @Test
    public void testSearchByTitleAndDescription() {
        String searchTitle = "Pink Floyd album";
        String searchDescription = "album by Pink Floyd";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipSettings();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchTitle);
        SearchPageObject.waitForElementByTitleAndDescription(searchTitle, searchDescription);

        List<String> articlesTitles = SearchPageObject.getArticleListBySearch();

        assertTrue(articlesTitles.size() >= 3);
    }
}
