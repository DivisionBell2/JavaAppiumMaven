package lesson5;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import java.util.List;

public class TaskTwoTest extends CoreTestCase {

    // Адаптировать по MW тест на поиск и верификацию трех результатов выдачи поиска.
    @Test
    public void testSearchByTitleAndDescription() {
        String searchTitle = "Pink Floyd album";
        String searchDescription = "album by Pink Floyd";

        String mwSearchTitle = "Pink Floyd";
        String mwSearchDescription = "English rock band";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.skipSettings();
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(searchTitle);
            SearchPageObject.waitForElementByTitleAndDescription(searchTitle, searchDescription);
        }

        if (Platform.getInstance().isMw()) {
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(mwSearchTitle);
            SearchPageObject.waitForElementByTitleAndDescription(mwSearchTitle, mwSearchDescription);
        }



        List<String> articlesTitles = SearchPageObject.getArticleListBySearch();

        assertTrue(articlesTitles.size() >= 3);
    }
}
