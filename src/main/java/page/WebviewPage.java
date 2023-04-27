package page;

import data.CommonData;
import data.LocatorData;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import util.Actions;
import util.Constant;

public class WebviewPage {
    //Elements By
    private static String getWebviewPackage(String browser){
        String webviewPackage = null;
        if(browser.equals(CommonData.Chrome)){
            webviewPackage = Constant.CHROME_APP_PACKAGE;
        }
        return webviewPackage;
    }

    //Actions
    public static void closeWebview(String browser) {
        By byButtonClose = AppiumBy.id(LocatorData.idButtonClose.replace("<package>",getWebviewPackage(browser)));
        Actions.clickElement(byButtonClose);
        Actions.waitUntilElementNotDisplayed(byButtonClose,3);
    }
}
