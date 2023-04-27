package page.android;

import config.GeneralConfig;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import util.Actions;
import util.DeviceHelper;

public class HomePage {
    AndroidDriver driver;

    public HomePage(AppiumDriver driver){
        this.driver = (AndroidDriver) driver;
    }

    // List of locator
    public static String buttonOK_id = GeneralConfig.appPackage + ":id/primary_button";

    // List of By inspector
    public static By buttonOKPopup = AppiumBy.id(buttonOK_id);

    // List of actions
    public void bypassPopup() {
        Actions.waitForElementToDisplay(buttonOKPopup,5);
        if(Actions.isElementDisplayed(buttonOKPopup)){
            Actions.clickElement(buttonOKPopup);
        }
    }

    public void selectMenu(String menu) {
        String[] splitMenu = menu.split("->");
        for (int i = 0; i < splitMenu.length; i++) {
            DeviceHelper.wait(500);
            // Click menu
            Actions.clickElement(AppiumBy.xpath("//*[@text='" + splitMenu[i] + "']"));
        }
    }

}
