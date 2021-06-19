package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://button[@id='searchIcon']";
        SEARCH_INPUT = "xpath://form/input[@type='search' and @name='search']";
        SEARCH_CANCEL_BUTTON = "xpath://div[@class='header-action']/button[contains(@class, 'close')]";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://a[contains(., '{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
