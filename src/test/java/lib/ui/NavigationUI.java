package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class NavigationUI extends MainPageObject {
    protected static String
        MY_LISTS_LINK,
        OPEN_NAVIGATION;

    public void openNavigation() {
        if (Platform.getInstance().isMw()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation button", 5);
        } else {
            System.out.println("Method swipeUp() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickMyLists() {

        if (Platform.getInstance().isMw()) {
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My List",
                    5
            );
        }
    }
}
