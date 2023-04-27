package page.ios;

import config.GeneralConfig;
import data.LoginData;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import util.Actions;

public class LoginPage {

    IOSDriver driver;

    public LoginPage(AppiumDriver driver){
        this.driver = (IOSDriver) driver;
        PageFactory.initElements(driver,this);

    }

    // List of locator
    public static String buttonOK_id = "OK";
    public static String buttonChooseOthersSignInOption_ClassChain = "**/XCUIElementTypeStaticText[`label == '"+ LoginData.TEXT_Choose_others_sign_in_option + "'`]";
    public static String titleNotice_iOSClassChain = "**/XCUIElementTypeAlert[`label == 'Notice'`]/**/XCUIElementTypeStaticText[1]";
    public static String contentNotice_iOSClassChain = "**/XCUIElementTypeAlert[`label == 'Notice'`]/**/XCUIElementTypeStaticText[2]";
    public static String titleSecurePopup_id = LoginData.TEXT_Secure_Play_Now_Account;
    public static String buttonContinute_iOSClassChain = "**/XCUIElementTypeStaticText[`label == '"+ LoginData.TEXT_Continue + "'`]";

    // List of By
    public static By buttonOKPopup = AppiumBy.accessibilityId(buttonOK_id);
    public static By buttonChooseOthersSignInOption = AppiumBy.iOSClassChain(buttonChooseOthersSignInOption_ClassChain);
    public static By titleNoticePopup = AppiumBy.iOSClassChain(titleNotice_iOSClassChain);
    public static By contentNoticePopup = AppiumBy.iOSClassChain(contentNotice_iOSClassChain);
    public static By titleSecurePopup = AppiumBy.xpath(titleSecurePopup_id);
    public static By buttonContinuteSecurePopup = AppiumBy.id(buttonContinute_iOSClassChain);

    // List of actions
    public void selectLoginChannel(String channel) {
        if (!channel.equals(LoginData.Sign_in_with_Apple)) {
            Actions.clickElement(buttonChooseOthersSignInOption);
        }
        By channelButton = AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == '" + channel + "'`]");
        Actions.clickElement(channelButton);
    }

    public String getPopupMessageContent() {
        Actions.waitUntilElementDisplayed(contentNoticePopup,5);
        return Actions.getText(contentNoticePopup);
    }

    public void handleSecureAccount() {
        if(Actions.isElementDisplayed(titleSecurePopup)){
            Actions.clickElement(buttonContinuteSecurePopup);
        }
    }
}
