package config;

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

public class IOSConfig extends GeneralConfig {

    @BeforeClass
    public void configureAppium() throws IOException {
        // Get execute properties value
        String pathProp = getClass().getClassLoader().getResource("execute_IOS.properties").getPath();
        Properties prop=new Properties();
        FileInputStream ip= new FileInputStream(pathProp);
        prop.load(ip);

        // Start driver
        XCUITestOptions iosOption =
                new XCUITestOptions()
                        .setPlatformName(prop.getProperty("device_platform"))
                        .setPlatformVersion(prop.getProperty("device_platform_version"))
                        .setDeviceName(prop.getProperty("device_name"))
                        .setUdid(prop.getProperty("device_udid"))
                        .setUpdatedWdaBundleId(prop.getProperty("wda_bundle_id"))
                        .setCommandTimeouts(Duration.ofSeconds(240))
                        .setBundleId(prop.getProperty("app_bundle_id"))
                        .setApp(prop.getProperty("app_file"))
                        .setAutomationName("XCUITest")
                        .setClearSystemFiles(true)
                        .setAutoAcceptAlerts(false);
        iosOption.setCapability("xcodeOrgId",prop.getProperty("xcodeOrgId"));
        iosOption.setCapability("xcodeSigningId",prop.getProperty("xcodeSigningId"));
        try {
            driver = new IOSDriver(new URL("http", Constant.APPIUM_SERVER_HOST, Integer.parseInt(Constant.APPIUM_SERVER_PORT), Constant.APPIUM_SERVER_PATH), iosOption);
        } catch (SessionNotCreatedException e) {
            iosOption.useNewWDA();
            driver = new IOSDriver(new URL("http", Constant.APPIUM_SERVER_HOST, Integer.parseInt(Constant.APPIUM_SERVER_PORT), Constant.APPIUM_SERVER_PATH), iosOption);
        }

        // Set variable
        appBundleID = prop.getProperty("app_bundle_id");
    }

}
