package matrix.android.app;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/* TODO slighty off-topic, but when printing on screen a big matrix appium fails to detect
   when it has finished. Is possible that printing a big matrix locks the main ui thread and
   that leaves appium without access to elements, but why when is finished it does not get
   access back?
*/
public class App {
    public static void main(String[] args) {
        Map parsedArgs = Parser.parse(args);

        /****************************************************************************************************/

        boolean print = false;
        int systemPort = 8199;
        if (parsedArgs.get("print") != null) {
            print = true;
        }
        if (parsedArgs.get("system_port") != null) {
            systemPort = (int) parsedArgs.get("system_port");
        }

        String size = parsedArgs.get("size").toString();
        String module = parsedArgs.get("module").toString();
        String httpEndpoint = parsedArgs.get("http_endpoint").toString();

        JSONConfig jsonConfig = new JSONConfig();
        DeviceCapabilities deviceCapabilities = null;
        if (parsedArgs.get("device") != null) {
            deviceCapabilities = new DeviceCapabilities(
                    parsedArgs.get("device").toString(),
                    systemPort
            );
        } else {
            deviceCapabilities = jsonConfig.getDevice();
        }
        AppiumSession session = new AppiumSession(
                jsonConfig.getHub(),
                deviceCapabilities
        );


        MainPage matrixApp = new MainPage(session);

        matrixApp.fillData(size, module, print, httpEndpoint);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss SSS");
        System.out.println("Job started at: " + dateFormat.format(new Date()));
        matrixApp.compute();

        while (!matrixApp.isFinished()) {
            // TODO I hate sleep, I want no sleep, sleep is bad, maybe a custom ExpectedCondition is better
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Job finished at: " + dateFormat.format(new Date()));
        System.out.println("Data from computation:");
        System.out.println(matrixApp.getData());
        matrixApp.quit();
    }
}
