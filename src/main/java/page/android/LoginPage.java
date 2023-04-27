package page.android;

import config.GeneralConfig;
import data.LoginData;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import util.Actions;

public class LoginPage {

    AndroidDriver driver;

    public LoginPage(AppiumDriver driver){
        this.driver = (AndroidDriver) driver;
    }

    // List of locator
    public static String buttonOK_id = "android:id/button1";
    public static String titleNoticeLogin_id = "android:id/alertTitle";
    public static String contentNoticeLogin_id = "android:id/message";
    public static String buttonContinute_id = GeneralConfig.appPackage + ":id/tvBack";
    public static String titleSecurePopup_xpath = "//android.widget.TextView[@text='"+ LoginData.TEXT_Secure_Play_Now_Account +"']";
    public static String titleNotice_id = GeneralConfig.appPackage + ":id/lblTitle";
    public static String contentNotice_id = GeneralConfig.appPackage + ":id/lblMessage";
    public static String buttonRetryNotice_id = GeneralConfig.appPackage + ":id/viewButton";

    // List of By
    public static By buttonOKNoticeLoginPopup = AppiumBy.id(buttonOK_id);
    public static By titleNoticeLoginPopup = AppiumBy.id(titleNoticeLogin_id);
    public static By contentNoticeLoginPopup = AppiumBy.id(contentNoticeLogin_id);
    public static By buttonContinuteSecurePopup = AppiumBy.id(buttonContinute_id);
    public static By titleSecurePopup = AppiumBy.xpath(titleSecurePopup_xpath);
    public static By titleNoticePopup = AppiumBy.id(titleNotice_id);
    public static By contentNoticePopup = AppiumBy.id(contentNotice_id);
    public static By buttonRetryNoticePopup = AppiumBy.id(buttonRetryNotice_id);

    // List of actions
    public void selectLoginChannel(String channel) {
        By channelButton = AppiumBy.xpath("//android.widget.TextView[@text='" + channel +"']/ancestor::android.widget.LinearLayout[1]");
        Actions.clickElement(channelButton);
    }

    public String getPopupMessageContent() {
        Actions.waitUntilElementDisplayed(contentNoticeLoginPopup,5);
        return Actions.getText(contentNoticeLoginPopup);
    }

    public void handleSecureAccount() {
        Actions.waitForElementToDisplay(titleSecurePopup,3);
        if(Actions.isElementDisplayed(titleSecurePopup)){
            Actions.clickElement(buttonContinuteSecurePopup);
        }
    }



}
