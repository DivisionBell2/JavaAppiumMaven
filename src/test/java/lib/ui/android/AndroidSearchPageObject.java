package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SKIP_BUTTON = "xpath://*[contains(@text, 'SKIP')]";
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_CANCEL_BUTTON = "xpath://*[@content-desc='Clear query']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[contains(@text, '{SUBSTRING}')]";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[contains(@text, '{SUBSTRING1}')]/following-sibling::android.widget.TextView[contains(@text, '{SUBSTRING2}')]";
        TITLE_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
        GO_TO_HOME_PAGE_BUTTON = "xpath://*[@class='android.widget.ImageButton']";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
