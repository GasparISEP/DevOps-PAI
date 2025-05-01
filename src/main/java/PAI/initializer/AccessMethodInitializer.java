package PAI.initializer;

import PAI.controller.US02_ConfigureAccessMethodController;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class AccessMethodInitializer {

    @Autowired
    private US02_ConfigureAccessMethodController controller;

    @PostConstruct
    public void init() {
        long startTime = System.nanoTime();
        try (InputStream is = getClass().getResourceAsStream("/AccessMethodData.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String name = line.trim();
                if (!name.isEmpty()) {
                    controller.configureAccessMethod(name);
                }
            }
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;
            System.out.printf("Initialization completed in %d ms%n", duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}