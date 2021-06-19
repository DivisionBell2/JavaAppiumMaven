package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SKIP_BUTTON = "xpath://*[contains(@text, 'SKIP')]";
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{SUBSTRING}')]";
        // Предполагаемый xpath для iOS
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeLink[contains(@text, '{SUBSTRING1}') and contains(@text, '{SUBSTRING2}')]";
        TITLE_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
        GO_TO_HOME_PAGE_BUTTON = "xpath://*[@class='android.widget.ImageButton']";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
    }

    public IOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
