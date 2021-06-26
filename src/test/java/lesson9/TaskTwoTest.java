package lesson9;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TaskTwoTest extends CoreTestCase {

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

        Assert.assertTrue(articlesTitles.size() >= 3);
    }
}
