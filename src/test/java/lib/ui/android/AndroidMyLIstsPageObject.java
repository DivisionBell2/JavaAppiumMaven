package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyLIstsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[contains(@text, '{FOLDER_NAME}')]";
        ARTICLE_BY_TITLE_TPL = "xpath://*[contains(@text, '{TITLE}')]";
        NOT_ARTICLES_MESSAGE = "xpath://*[contains(@text, 'You have no articles added to this list.')]";
    }

    public AndroidMyLIstsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
