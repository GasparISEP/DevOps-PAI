package PAI.initializer;

import PAI.controller.US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.FileReader;

@Configuration
@Profile("studyPlan")
public class StudyPlanInitializer {

    @Bean
    public CommandLineRunner loadDataRegisterStudyPlan(US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller) {
        return (args) -> {
            loadStudents(controller);
        };
    }

    private void loadStudents(US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller) {
        String csvFile = "src/main/resources/StudyPlan_Data.csv";

        long startTime = System.currentTimeMillis();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                line = line.replace("\uFEFF", "");
                String[] fields = line.split(";");

                String programmeName = fields[0];
                String programmeAcronym = fields[1];
                String startDate = fields[2];

                controller.registerStudyPlan(programmeName, programmeAcronym, startDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("\nStudy plan data loading time: " + duration + " ms\n");
    }
}
