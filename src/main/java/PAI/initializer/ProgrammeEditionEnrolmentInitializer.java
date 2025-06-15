package PAI.initializer;

import PAI.VOs.*;
import PAI.service.programmeEditionEnrolment.IProgrammeEditionEnrolmentService;
import org.springframework.context.annotation.Configuration;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.UUID;

@Configuration
public class ProgrammeEditionEnrolmentInitializer {

    public void loadProgrammeEditionEnrolment(IProgrammeEditionEnrolmentService service, String csvFilePath) {

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

                if (fields.length == 4) {
                    try {
                        int studentNum = Integer.parseInt(fields[0].trim());
                        Acronym programmeAcronym = new Acronym(fields[1].trim());
                        UUID schoolYear = UUID.fromString(fields[2].trim());
                        UUID generatedID = UUID.fromString(fields[3].trim());

                        SchoolYearID schoolYearID = new SchoolYearID(schoolYear);
                        StudentID studentID = new StudentID(studentNum);
                        ProgrammeID programmeID = new ProgrammeID(programmeAcronym);
                        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
                        //controller.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentID,programmeID,schoolYearID);
                        service.enrolStudentInProgrammeEdition(studentID,  programmeEditionID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\nProgrammeEditionEnrolment loading time: " + (endTime - startTime) + " ms\n");
    }
}
