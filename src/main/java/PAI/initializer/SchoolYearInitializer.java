package PAI.initializer;

import PAI.controller.US07_IWantToCreateASchoolYearController;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;

@Configuration
public class SchoolYearInitializer {

    public void loadSchoolYear(US07_IWantToCreateASchoolYearController controller, String csvFilePath) {

        long startTime = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                line = line.replace("\uFEFF", "");
                String[] fields = line.split(",");
                if (fields.length < 4) continue;

                String schoolYearID = fields[0].trim();
                String yearDescription =  fields[1].trim();
                String startDate = fields[2].trim();
                String endDate = fields[3].trim();

                controller.addSchoolYear(schoolYearID, yearDescription, startDate,endDate);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("\nSchoolYear data loading time: " + (endTime - startTime) + " ms\n");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load SchoolYear from CSV", e);
        }
    }
}