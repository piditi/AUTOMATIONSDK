package util;

import config.AndroidConfig;
import config.IOSConfig;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.time.Duration;

public class IOSActions extends IOSConfig {
    static IOSDriver driver = (IOSDriver) IOSConfig.driver;

    public static void runAppInBackGround(int second) {
        driver.runAppInBackground(Duration.ofSeconds(second));
    }

    public static void activeApp(String bundleID) {
        driver.activateApp(bundleID);
    }

    public static void terminateApp(String bundleID) {
        driver.terminateApp(bundleID);
    }
}
