package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListsPageObject {
    static {

        ARTICLE_BY_TITLE_TPL = "xpath://ul//h3[contains(text(), '{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul//h3[contains(text(), 'Java')]/../../a[contains(@class, 'watch-this-article watched')]";
    }

    public MWMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
