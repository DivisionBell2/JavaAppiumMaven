package lesson4;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class TaskThreeTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("orientation", "PORTRAIT");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "D:/study/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "Authorization"), @Feature(value = "Navigation"), @Feature(value = "My lists")})
    @DisplayName("First test")
    @Description("This test is the first and was not refactored (it was saved for history)")
    @Step("Start firstTest")
    @Severity(SeverityLevel.TRIVIAL)
    public void firstTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Cancel search")
    @Description("We cancel search before searching")
    @Step("Start testCancelSearch")
    @Severity(SeverityLevel.TRIVIAL)
    public void testCancelSearch() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='Clear query']"),
                "Cannot find X to cancel Search",
                5
        );

        waitForElementNotPresent(
                By.xpath("//android.widget.ImageView[@content-desc='Clear query']"),
                "X is still present on the page",
                5
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Cancel search and clear")
    @Description("We are cancel search and clear result list")
    @Step("Start testCancelSearchAndClear")
    @Severity(SeverityLevel.TRIVIAL)
    public void testCancelSearchAndClear() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClear(
                By.xpath("//*[contains(@text, 'Java')]"),
                "Cannot clear the Search input",
                5
        );

        waitForElementNotPresent(
                By.xpath("//android.widget.ImageView[@content-desc='Clear query']"),
                "X is still present on the page",
                5
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    // METHODS

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
}
