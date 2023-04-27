package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.SessionNotCreatedException;
import org.testng.annotations.BeforeClass;
import util.Constant;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class AndroidConfig extends GeneralConfig{
    @BeforeClass
    public void configureAppium() throws IOException {
        // Get execute properties value
        String pathProp = getClass().getClassLoader().getResource("execute_Android.properties").getPath();
        Properties prop=new Properties();
        FileInputStream ip= new FileInputStream(pathProp);
        prop.load(ip);

        // Start driver
        UiAutomator2Options androidOption =
                new UiAutomator2Options()
                        .setNoReset(true)
                        .setAutomationName("uiautomator2")
                        .setClearSystemFiles(true)
                        .setAllowTestPackages(true)
                        .setPlatformName(Constant.OS_ANDROID)
                        .setAutoWebview(true)
                        .setDeviceName(prop.getProperty("device_name"))
                        .setApp(prop.getProperty("app_file"))
                        .setAutoGrantPermissions(true);
        if(appPackage!=null){
            androidOption.setAppPackage(appPackage);
        }
        if(appActivity!=null){
            androidOption.setAppActivity(appActivity);
        }
        androidOption.setCapability("–-session-override",true);
        androidOption.setCapability("relaxedSecurity",true);
        androidOption.setCapability("no",true);
        driver = new AndroidDriver(new URL("http", Constant.APPIUM_SERVER_HOST, Integer.parseInt(Constant.APPIUM_SERVER_PORT), Constant.APPIUM_SERVER_PATH), androidOption);

        // Set variable
        appPackage = prop.getProperty("app_package");
        appActivity = prop.getProperty("app_activity");
    }
}
