package matrix.android.app;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        boolean print = false;
        try {
            Integer.parseInt(args[0]);
            Integer.parseInt(args[1]);
            print = Boolean.parseBoolean(args[2]);
        } catch (Exception ex) {
            System.out.println("Something is wrong with your arguments");
            System.exit(1);
        }

        String size = args[0], module = args[1];
        JSONConfig jsonConfig = new JSONConfig();


        AppiumSession session = new AppiumSession(
                jsonConfig.getHub(),
                jsonConfig.getDevice()
        );
        MainPage matrixApp = new MainPage(session);

        matrixApp.fillData(size, module, print);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss SSS");
        System.out.println("Job started at: " + dateFormat.format(new Date()));
        matrixApp.compute();
        while (!matrixApp.isFinished()) {}
        System.out.println("Job finished at: " + dateFormat.format(new Date()));
        System.out.println("Data from computation:");
        System.out.println(matrixApp.getData());
        matrixApp.quit();
    }
}
