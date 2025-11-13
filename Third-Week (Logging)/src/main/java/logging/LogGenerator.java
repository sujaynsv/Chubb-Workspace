package logging;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogGenerator {

    private static final Logger logger = LoggerFactory.getLogger(LogGenerator.class);

    public static void generateLogs() {
        logger.info("Application started successfully.");
        logger.warn("Low disk space detected.");
        logger.error("Database connection failed!");
        logger.info("User login successful: alice");
        logger.error("PaymentService: Payment failed for transaction T102");
    }

    public static void main(String[] args) {
        generateLogs();
        System.out.println("Logs written to logs/app.log");
    }
}
