package lib.ui;

import io.qameta.allure.Step;
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

    @Step("Waiting for title on the article page")
    public WebElement waitForTitleElement(String substring) {
        String article_result_xpath = getArticleElement(substring);
        return this.waitForElementPresent(article_result_xpath, "Cannot find article title on page with substring" + substring, 15);
    }

    @Step("Waiting for title element without waiting on the article page")
    public void waitForTitleElementWithoutWaiting(String substring) {
        String article_result_xpath = getArticleElement(substring);
        this.checkElementPresent(article_result_xpath);
    }

    @Step("Get article title")
    public String getArticleTitle(String substring) {
        String title_element = getArticleElement(substring);
        return this.waitForElementAndGetAttribute(
                title_element,
                "content-desc",
                "Cannot find article title with  name " + substring,
                15

        );
    }

    @Step("Swiping to footer on article page")
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

    @Step("Adding the article to my list")
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

    @Step("Adding the article in list '{name_of_folder}'")
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

    @Step("Adding the article to my saved articles")
    public void addArticlesToMySaved() {

        if (Platform.getInstance().isMw()) {
            this.removeArticleFromSavedIfItAdded();
        }

        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    @Step("Removing the article from saved if it has been added")
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

    @Step("Closing the article")
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