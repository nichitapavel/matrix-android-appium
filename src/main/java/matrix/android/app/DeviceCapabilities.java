package matrix.android.app;

import com.google.gson.JsonElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DeviceCapabilities {
    DesiredCapabilities desiredCap = new DesiredCapabilities();

    public DeviceCapabilities(JsonElement json) {
        this.desiredCap.setPlatform(Platform.ANDROID);
        this.desiredCap.setCapability("appPackage","es.ull.hpcg.matrixandroidapp");
        this.desiredCap.setCapability("appActivity", "MainActivity");
        this.desiredCap.setCapability(MobileCapabilityType.UDID, json.getAsJsonObject().get("udid"));
        this.desiredCap.setCapability(MobileCapabilityType.DEVICE_NAME, json.getAsJsonObject().get("name"));
    }

    public DeviceCapabilities(String device) {
        this.desiredCap.setPlatform(Platform.ANDROID);
        this.desiredCap.setCapability("appPackage","es.ull.hpcg.matrixandroidapp");
        this.desiredCap.setCapability("appActivity", "MainActivity");
        this.desiredCap.setCapability(MobileCapabilityType.UDID, device);
        this.desiredCap.setCapability(MobileCapabilityType.DEVICE_NAME, "default");
    }

    public DesiredCapabilities getCapabilities() {
        return this.desiredCap;
    }

}
