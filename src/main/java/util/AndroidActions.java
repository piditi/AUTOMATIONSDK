package util;

import config.AndroidConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AndroidActions extends AndroidConfig {
    static AndroidDriver driver = (AndroidDriver) AndroidConfig.driver;

    public static void runAppInBackGround(int second) {
        driver.runAppInBackground(Duration.ofSeconds(second));
    }

    public static void activeApp(String appPackage) {
        driver.activateApp(appPackage);
    }

    public static void startApp(String appPackage,String appActivity) {
        Activity act = new Activity(appPackage,appActivity);
        act.setStopApp(false);
        driver.startActivity(act);
    }

    // turn on all (data and wi-fi)
    public static void turnOnAllData() {
        driver.setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
        driver.setConnection(new ConnectionStateBuilder().withDataEnabled().build());
        driver.setConnection(new ConnectionStateBuilder().withAirplaneModeDisabled().build());
    }

    // turn off all (data and wi-fi)
    public static void turnOffAllData() {
        //driver.setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
        //driver.setConnection(new ConnectionStateBuilder().withAirplaneModeEnabled().build());
        Map<String, Object> argv = new HashMap<>();
        argv.put("command", "cmd ");
        argv.put("args", "-w wifi set-wifi-enabled disabled");
        String result = driver.executeScript("mobile: shell", argv).toString();
        System.out.println(result);
    }

}
