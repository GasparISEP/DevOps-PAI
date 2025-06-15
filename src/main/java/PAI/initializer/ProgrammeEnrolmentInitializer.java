package PAI.initializer;

import PAI.controller.US09_EnrolStudentInProgrammeController;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.UUID;

@Configuration
public class ProgrammeEnrolmentInitializer {

    public void loadProgrammeEnrolment(US09_EnrolStudentInProgrammeController controller, String csvFilePath) {

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
