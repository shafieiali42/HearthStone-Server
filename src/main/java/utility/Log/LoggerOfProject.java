package utility.Log;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerOfProject implements Serializable {
    static FileHandler fileTxt;
    static SimpleFormatter formatterTxt;
//    public static final Logger logger = Logger.getLogger("");

    public static Logger getMyLogger(String FilePath)  {

//        logger.setLevel(Level.INFO);
        try {
            Logger logger = Logger.getLogger("");
            logger.removeHandler(logger.getHandlers()[0]);
            fileTxt = new FileHandler(FilePath, true);
            formatterTxt = new SimpleFormatter();
            fileTxt.setFormatter(formatterTxt);
            logger.addHandler(fileTxt);
            return logger;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

