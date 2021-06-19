package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {


        TITLE_TPL = "xpath://h1[@id='section_0']";
        FOOTER_ELEMENT = "xpath://footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://a[@id='ca-watch']";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "xpath://a[@id='ca-watch' and @title='Add this page to your watchlist']";
//        SAVE_BUTTON = "xpath://footer";
//        ADD_TO_LIST_BUTTON = "xpath://a[@id='ca-watch']";


        SAVE_FOLDER_NAME_LIST_INPUT = "xpath://*[@resource-id='org.wikipedia:id/text_input' and @text='Name of this list']";
        ADD_TO_LIST_OK_BUTTON = "xpath://*[contains(@text, 'OK')]";
        //CLOSE_ARTICLE_BUTTON = "xpath://*[@content-desc='Navigate up']";
        ARTICLE_NAME_TPL = "xpath://*[@content-desc='{SUBSTRING}']";
        EXISTED_FOLDER_FOR_ARTICLES = "xpath://*[contains(@text, '{SUBSTRING}')]";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
