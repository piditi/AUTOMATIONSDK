package page.ios;

import data.LoginData;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Actions;
import util.DeviceHelper;

import java.time.Duration;

public class HomePage {
    IOSDriver driver;

    public HomePage(AppiumDriver driver){
        this.driver = (IOSDriver) driver;
    }

    // List of locator
    public static String buttonOK_id = "card-primary-action-button";
    public static String buttonContinue_iOSClassChain = "**/XCUIElementTypeButton[`label == '"+ LoginData.TEXT_Continue + "'`]";
    public static String contentNotice_iOSClassChain = "**/XCUIElementTypeStaticText[`label == 'Notice'`]";

    // List of By inspector
    public static By buttonOKPopup = AppiumBy.accessibilityId(buttonOK_id);
    public static By buttonContinuePopup = AppiumBy.iOSClassChain(buttonContinue_iOSClassChain);
    public static By contentNoticePopup = AppiumBy.iOSClassChain(contentNotice_iOSClassChain);

    // List of actions
    public void bypassPopup() {
        Actions.waitForElementToDisplay(buttonOKPopup,10);
        if(Actions.isElementDisplayed(buttonOKPopup)){
            Actions.clickElement(buttonOKPopup);
        }
        if(Actions.isElementDisplayed(buttonContinuePopup)){
            Actions.clickElement(buttonContinuePopup);
        }
        if(Actions.isElementDisplayed(buttonContinuePopup)){
            Actions.clickElement(buttonContinuePopup);
        }
        Boolean alertDisplayed = true;
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        do {
            try {
                webDriverWait.until(ExpectedConditions.alertIsPresent());
                driver.switchTo().alert().accept();
            } catch (Exception e) {
                alertDisplayed = false;
            }
        } while (alertDisplayed);
    }

    public void selectMenu(String menu) {
        String[] splitMenu = menu.split("->");
        for (int i = 0; i < splitMenu.length; i++) {
            DeviceHelper.wait(500);
            // Click menu
            By menuToClick = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == '" + splitMenu[i] + "'`]");
            Actions.clickElement(menuToClick);
        }
    }

    public void handlePopup(WebElement button) {
        Actions.clickElement(buttonOKPopup);
    }
}
