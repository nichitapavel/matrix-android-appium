package matrix.android.app;

import java.net.URL;
import java.util.Map;

public class Config {
    private URL hub;
    private String size;
    private String module;
    private String httpEndpoint;
    private DeviceCapabilities device;
    private boolean print;

    public static Config getConfig(String[] args) {
        Map parsedArgs = Parser.parse(args);
        JSONConfig jsonConfig = new JSONConfig();
        DeviceCapabilities deviceCapabilities;
        boolean print = false;

        if (parsedArgs.get("print") != null) {
            print = true;
        }

        if (parsedArgs.get("device") != null) {
            deviceCapabilities = new DeviceCapabilities(
                    parsedArgs.get("device").toString(),
                    (Integer) parsedArgs.get("system_port")
            );
        } else {
            deviceCapabilities = jsonConfig.getDevice();
        }

        String size = parsedArgs.get("size").toString();
        String module = parsedArgs.get("module").toString();
        String httpEndpoint = parsedArgs.get("http_endpoint").toString();

        return new Config(jsonConfig.getHub(), size, module, httpEndpoint, print, deviceCapabilities);
    }

    public Config(URL hub, String size, String module, String httpEndpoint, boolean print, DeviceCapabilities device) {
        this.hub = hub;
        this.size = size;
        this.module = module;
        this.httpEndpoint = httpEndpoint;
        this.print = print;
        this.device = device;
    }

    public String getSize() {
        return size;
    }

    public String getModule() {
        return module;
    }

    public String getHttpEndpoint() {
        return httpEndpoint;
    }

    public boolean isPrint() {
        return print;
    }

    public URL getHub() {
        return this.hub;
    }

    public DeviceCapabilities getDevice() {
        return this.device;
    }
}
