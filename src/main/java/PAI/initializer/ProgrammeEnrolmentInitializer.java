package PAI.initializer;

import PAI.controller.US09_EnrolStudentInProgrammeController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.UUID;

@Configuration
@Profile("programmeEnrolment")
public class ProgrammeEnrolmentInitializer {

    @Bean
    public CommandLineRunner loadDataProgrammeEnrolment(US09_EnrolStudentInProgrammeController controller) {
        return (args) -> {
            loadProgrammeEnrolment(controller);
        };
    }

    private void loadProgrammeEnrolment(US09_EnrolStudentInProgrammeController controller) {
        String csvFile = "src/main/resources/ProgrammeEnrolment_Data.csv";

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

                int studentNumber = Integer.parseInt(fields[0]);
                UUID accessMethodIDStr = UUID.fromString(fields[1]);
                String programmeName = fields[2];
                String acronym = fields[3];
                String enrolmentDate = fields[4];

                controller.enrolStudentInProgramme(studentNumber, accessMethodIDStr, programmeName, acronym, enrolmentDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("\nProgrammeEnrolment data loading time: " + duration + " ms\n");
    }
}
