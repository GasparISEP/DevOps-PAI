package PAI.initializer;

import PAI.controller.US02_ConfigureAccessMethodController;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
public class AccessMethodInitializer {

    public void loadAccessMethod(US02_ConfigureAccessMethodController controller, String csvFilePath) {

        long startTime = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

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