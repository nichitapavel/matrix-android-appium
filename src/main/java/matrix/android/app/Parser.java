package matrix.android.app;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.util.*;

public class Parser {

    public static final Map parse(String[] args) {
        ArgumentParser parser =
                ArgumentParsers.newFor("matrix-android-appium").build()
                .defaultHelp(true)
                .description(
                        "Set matrixes size, module and if printing result." +
                        "Set appium devices to use."
                );
        parser.addArgument("-s", "--size")
                .help("Specify the size of the matrixes")
                .type(int.class)
                .required(true);
        parser.addArgument("-m", "--module")
                .help("Module to fill the matrixes")
                .type(int.class)
                .required(true);
        parser.addArgument("-p", "--print")
                .help("Print the all matrixes")
                .type(boolean.class);
        parser.addArgument("-e", "--http-endpoint").
                help("Print the all matrixes")
                .type(String.class);
        parser.addArgument("-d", "--device")
                .help("adb udid of device to be used by appium")
                .required(false)
                .type(String.class);

        Namespace ns = null;
        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }

        return ns.getAttrs();
    }
}
