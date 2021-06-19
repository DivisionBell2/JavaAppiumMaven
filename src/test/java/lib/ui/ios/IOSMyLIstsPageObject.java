package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyLIstsPageObject extends MyListsPageObject {

    static {
        // Поиск осуществляется по тексту внутри элемента
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@text, '{TITLE}')]";
        NOT_ARTICLES_MESSAGE = "xpath://*[contains(@text, 'You have no articles added to this list.')]";
    }

    public IOSMyLIstsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
