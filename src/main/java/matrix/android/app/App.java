package matrix.android.app;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/* TODO slighty off-topic, but when printing on screen a big matrix appium fails to detect
   when it has finished. Is possible that printing a big matrix locks the main ui thread and
   that leaves appium without access to elements, but why when is finished it does not get
   access back?
*/
public class App {
    public static void main(String[] args) {

        Config config = Config.getConfig(args);
        AppiumSession session = new AppiumSession(
                config.getHub(),
                config.getDevice()
        );
        MainPage matrixApp = new MainPage(session);

        matrixApp.fillData(config.getSize(), config.getModule(), config.isPrint(), config.getHttpEndpoint());
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
