package util;

import config.GeneralConfig;

public class DeviceHelper extends GeneralConfig {
    public static Object getDeviceCapability(String capability) {
        return driver.getCapabilities().getCapability(capability);

    }

    public static void wait(int milliseconds) {
        {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
