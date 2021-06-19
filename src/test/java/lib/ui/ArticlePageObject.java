package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE,
            TITLE_TPL,
            FOOTER_ELEMENT,
            SAVE_BUTTON,
            ADD_TO_LIST_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            SAVE_FOLDER_NAME_LIST_INPUT,
            ADD_TO_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            ARTICLE_NAME_TPL,
            EXISTED_FOLDER_FOR_ARTICLES;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getArticleElement(String substring) {
        return TITLE_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getFolderElement(String substring) {
        return EXISTED_FOLDER_FOR_ARTICLES.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE METHODS */

    public WebElement waitForTitleElement(String substring) {
        String article_result_xpath = getArticleElement(substring);
        return this.waitForElementPresent(article_result_xpath, "Cannot find article title on page with substring" + substring, 15);
    }

    public void waitForTitleElementWithoutWaiting(String substring) {
        String article_result_xpath = getArticleElement(substring);
        this.checkElementPresent(article_result_xpath);
    }

    public String getArticleTitle(String substring) {
        String title_element = getArticleElement(substring);
        return this.waitForElementAndGetAttribute(
                title_element,
                "content-desc",
                "Cannot find article title with  name " + substring,
                15

        );
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    20
            );
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    20
            );
        } else {
            this.scrollWebPageTitleElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        }

    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndSendKeys(
                SAVE_FOLDER_NAME_LIST_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void addArticleToExistedList(String name_of_folder) {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        String name_of_folder_xpath = getFolderElement(name_of_folder);
        this.waitForElementAndClick(
                name_of_folder_xpath,
                "Cannot find reading list with name " + name_of_folder,
                5
        );
    }

    public void addArticlesToMySaved() {

        if (Platform.getInstance().isMw()) {
            this.removeArticleFromSavedIfItAdded();
        }

        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1
            );

            this.waitForElementPresent(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from this list before"
            );
        }
    }

    public void closeArticle() {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot press navigate up button",
                    5
            );
        } else {
            System.out.println("Method swipeUp() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
}