package utility.constant;


import utility.config.ConfigLoader;

import java.io.IOException;
import java.util.Properties;

public class Constant {





    public static int widthOfMainFrame = 0;
    public static int heightOfMainFrame = 0;
    public static int defaultCloseOperation;
    public static boolean resizable;

    static {
        try {
            Properties properties = ConfigLoader.getInstance().readProperties("src/main/resources/configFiles/graphicConfigFiles/Panels/MyMainFrame/MainFrameConfigFiles.properties");
            widthOfMainFrame = Integer.parseInt(properties.getProperty("width"));
            heightOfMainFrame = Integer.parseInt(properties.getProperty("height"));
            defaultCloseOperation = Integer.parseInt(properties.getProperty("CloseOperation"));
            resizable = Boolean.parseBoolean(properties.getProperty("Resizable"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
