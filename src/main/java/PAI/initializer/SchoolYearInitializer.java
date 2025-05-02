package PAI.initializer;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.controller.US07_IWantToCreateASchoolYearController;
import PAI.domain.SchoolYear;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.UUID;

@Configuration
public class SchoolYearInitializer {

    private final US07_IWantToCreateASchoolYearController controller;

    public SchoolYearInitializer(US07_IWantToCreateASchoolYearController controller) {
        this.controller = controller;
    }

    @Bean
    public CommandLineRunner loadDataRegisterSchoolYear() {
        return args -> init();
    }

    public void init() {

        long startTime = System.currentTimeMillis();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("SchoolYear.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                line = line.replace("\uFEFF", "");
                String[] fields = line.split(",");
                if (fields.length < 3) continue;

                String yearDescription =  fields[0].trim();
                String startDate = fields[1].trim();
                String endDate = fields[2].trim();

                controller.addSchoolYear(yearDescription, startDate,endDate);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("\nSchoolYear data loading time: " + (endTime - startTime) + " ms\n");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load SchoolYear from CSV", e);
        }
    }
}





