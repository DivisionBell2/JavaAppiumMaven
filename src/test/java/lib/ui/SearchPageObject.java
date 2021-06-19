package lib.ui;

import com.sun.jna.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchPageObject extends MainPageObject {

    protected static String
            SKIP_BUTTON,
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL,
            TITLE_ELEMENT,
            GO_TO_HOME_PAGE_BUTTON,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT;

    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    // Адаптировать по iOS тест на поиск и верификацию трех результатов выдачи поиска.
    private static String getResultSearchElement(String substring, String substringDescription) {
        String temporary = SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{SUBSTRING1}", substring);
        return temporary.replace("{SUBSTRING2}", substringDescription);
    }
    /*TEMPLATE METHODS */

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void skipSettings() {
        this.waitForElementAndClick(SKIP_BUTTON, "Cannot find and click skip button", 5);
    }

    public void waitForInitSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init elemet");
    }

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click seach init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init elemet");
    }

    public void typeSearchLine(String search_line) {
        if (Platform.isAndroid()) {
            this.waitForElementAndSendKeys(SEARCH_INIT_ELEMENT, search_line, "Cannot find and type into search input", 5);
        } else {
            this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
        }
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    // Адаптировать по iOS тест на поиск и верификацию трех результатов выдачи поиска.
    public WebElement waitForElementByTitleAndDescription(String title, String description) {
        String search_result_xpath = getResultSearchElement(title, description);
        return this.waitForElementPresent(search_result_xpath, "Cannot find search result with title " + title + " and description " + description);
    }

    public void waitForCancelButtonAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);

    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void waitForArticleListIsEmpty() {
        this.waitForElementNotPresent(TITLE_ELEMENT, "Articles still presented is search", 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public void clickByArticle() {
        this.waitForElementAndClick(TITLE_ELEMENT, "Cannot find and click by title", 10);
    }

    public void navigateToHomePage() {
        this.waitForElementAndClick(
                GO_TO_HOME_PAGE_BUTTON,
                "Cannot press clear history button",
                5
        );
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request " + SEARCH_RESULT_ELEMENT,
                15
        );

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {

        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result label by the request",
                15
        );
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results"
        );
    }

    public String getSearchInitElementText() {
        return this.waitForElementAndGetAttribute(
                SEARCH_INIT_ELEMENT,
                "text",
                "Search input cannot have text",
                5
        );
    }

    public List<String> getArticleListBySearch() {
        List<String> article_titles_text = new ArrayList<>();
        List<WebElement> article_titles = this.waitForElementsPresent(
                TITLE_ELEMENT,
                "Cannot find articles by searching",
                15
        );

        for (WebElement title : article_titles) {
            article_titles_text.add(this.getWebElementText(title));
        }

        return article_titles_text;
    }
}
