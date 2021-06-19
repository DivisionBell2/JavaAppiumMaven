package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MyListsPageObject;
import lib.ui.android.AndroidMyLIstsPageObject;
import lib.ui.ios.IOSMyLIstsPageObject;
import lib.ui.mobile_web.MWMyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsPageObjectFactory {

    public static MyListsPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyLIstsPageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSMyLIstsPageObject(driver);
        } else {
            return new MWMyListPageObject(driver);
        }
    }
}
