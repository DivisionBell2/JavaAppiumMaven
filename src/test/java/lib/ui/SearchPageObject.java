package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
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

    @Step("Clicking on skip settings button")
    public void skipSettings() {
        this.waitForElementAndClick(SKIP_BUTTON, "Cannot find and click skip button", 5);
    }

    @Step("Waiting for appearing of search input")
    public void waitForInitSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init elemet");
    }

    @Step("Initializing the search field")
    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click seach init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init elemet");
    }

    @Step("Typing '{search_line}' to the search line")
    public void typeSearchLine(String search_line) {
        if (Platform.isAndroid()) {
            this.waitForElementAndSendKeys(SEARCH_INIT_ELEMENT, search_line, "Cannot find and type into search input", 5);
        } else {
            this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
        }
    }

    @Step("Clicking button to cancel search result")
    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    @Step("Wait for search result")
    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    @Step("Waiting for element with title '{title}' and description '{description}'")
    public WebElement waitForElementByTitleAndDescription(String title, String description) {
        String search_result_xpath = getResultSearchElement(title, description);
        return this.waitForElementPresent(search_result_xpath, "Cannot find search result with title " + title + " and description " + description);
    }

    @Step("Waiting for button to cancel search result")
    public void waitForCancelButtonAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);

    }

    @Step("Wait for search cancel button to disappear")
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    @Step("Waiting for article list is empty")
    public void waitForArticleListIsEmpty() {
        this.waitForElementNotPresent(TITLE_ELEMENT, "Articles still presented is search", 5);
    }

    @Step("Waiting for search result and select an article by substring in article title")
    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    @Step("Clicking by article name")
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

    @Step("Getting amount of found articles")
    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request " + SEARCH_RESULT_ELEMENT,
                15
        );

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Waiting for empty result label")
    public void waitForEmptyResultsLabel() {

        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result label by the request",
                15
        );
    }

    @Step("Making sure there are no results for the search")
    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results"
        );
    }

    @Step("Getting search init element text")
    public String getSearchInitElementText() {
        return this.waitForElementAndGetAttribute(
                SEARCH_INIT_ELEMENT,
                "text",
                "Search input cannot have text",
                5
        );
    }

    @Step("Getting article list by search")
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
