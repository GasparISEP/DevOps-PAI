package PAI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        SpringApplication.run(Main.class, args);
        long totalTime = System.currentTimeMillis() - startTime;
        logger.info("Total Application Startup Time: {} ms", totalTime);
    }
}