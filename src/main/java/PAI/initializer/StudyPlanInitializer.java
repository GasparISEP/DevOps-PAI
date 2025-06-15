package PAI.initializer;

import PAI.controller.US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;

@Configuration
public class StudyPlanInitializer {

    public void loadStudyPlan(US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller, String csvFilePath) {

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
                String[] fields = line.split(";");
                if (fields.length < 3) {
                    System.err.println("Linha inválida: " + line);
                    continue; // ignora linhas mal formatadas
                }

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
