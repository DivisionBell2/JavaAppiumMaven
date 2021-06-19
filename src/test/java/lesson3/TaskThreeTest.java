package lesson3;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import java.util.List;

public class TaskThreeTest extends CoreTestCase {

    @Test
    public void testCheckSearchHasExpectedWord() {
        String keyWord = "Java";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipSettings();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(keyWord);

        List<String> articlesTitles = SearchPageObject.getArticleListBySearch();

        for (String articleTitle : articlesTitles) {
            assertTrue(articleTitle.toLowerCase().contains(keyWord.toLowerCase()));
        }
    }
}