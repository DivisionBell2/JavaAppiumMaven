package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
    LOGIN_BUTTON = "xpath://div/a[contains(., 'Log in')]",
    LOGIN_INPUT = "xpath://input[@id='wpName1']",
    PASSWORD_INPUT = "xpath://input[@id='wpPassword1']",
    SUBMIT_BUTTON = "xpath://button[@id='wpLoginAttempt']";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Clicking on the authorization button")
    public void clickAuthButton() {
        this.tryClickElementWithFewAttempts(LOGIN_BUTTON, "Cannot find click auth button", 5);

    }

    @Step("Entering login data: '{login}' - for login, '{password}' - for password")
    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put a login to the login input", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put a login to the login input", 5);
    }

    @Step("Clicking on the submit button in authorization form")
    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submint auth button", 5);
    }
}
