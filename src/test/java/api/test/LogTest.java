package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogTest {
    // Open console
    // echo "Test Log Entry" > ./logs/test.log
    // test.log created

    private static final Logger logger = LogManager.getLogger(LogTest.class);

    public static void main(String[] args) {
        System.out.println("Log4j2 configuration file: " + System.getProperty("log4j.configurationFile"));

        logger.debug("**************** debugging ****************");
        logger.info("Testing Log4j2 configuration.");
    }
}

