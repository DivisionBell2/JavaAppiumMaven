package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE_TPL = "xpath://*[@content-desc='{SUBSTRING}']";
        FOOTER_ELEMENT = "xpath://*[@content-desc='View article in browser']";
        SAVE_BUTTON = "xpath://*[contains(@text, 'Save')]";
        ADD_TO_LIST_BUTTON = "xpath://*[contains(@text, 'ADD TO LIST')]";
        SAVE_FOLDER_NAME_LIST_INPUT = "xpath://*[@resource-id='org.wikipedia:id/text_input' and @text='Name of this list']";
        ADD_TO_LIST_OK_BUTTON = "xpath://*[contains(@text, 'OK')]";
        CLOSE_ARTICLE_BUTTON = "xpath://*[@content-desc='Navigate up']";
        ARTICLE_NAME_TPL = "xpath://*[@content-desc='{SUBSTRING}']";
        EXISTED_FOLDER_FOR_ARTICLES = "xpath://*[contains(@text, '{SUBSTRING}')]";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
