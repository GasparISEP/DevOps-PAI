package PAI.initializer;

import PAI.VOs.*;
import PAI.controller.US16_EnrolAStudentInACourseEditionController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.UUID;

@Configuration
public class CourseEditionEnrolmentInitializer {

    @Bean
    public void loadCourseEditionEnrolments(US16_EnrolAStudentInACourseEditionController controller, String csvFilePath) {
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

                if (fields.length == 9) {
                    try {
                        UUID ceeGeneratedUUID = UUID.fromString(fields[0].trim());
                        int studentNum = Integer.parseInt(fields[1].trim());
                        Acronym programmeAcronym = new Acronym(fields[2].trim());
                        UUID schoolYear = UUID.fromString(fields[3].trim());
                        int studyPlanStartYear = Integer.parseInt(fields[4].trim());
                        Acronym courseAcronym = new Acronym(fields[5].trim());
                        Name courseName = new Name(fields[6].trim());
                        LocalDate date = LocalDate.parse(fields[7].trim());
                        boolean isActive = Boolean.parseBoolean(fields[8].trim());

                        CourseEditionEnrolmentGeneratedID cceGeneraedid = new CourseEditionEnrolmentGeneratedID(ceeGeneratedUUID);
                        SchoolYearID schoolYearID = new SchoolYearID(schoolYear);
                        Date studyPlanYear = new Date(LocalDate.of(studyPlanStartYear,9,1));
                        StudentID studentID = new StudentID(studentNum);
                        ProgrammeID programmeID = new ProgrammeID(programmeAcronym);
                        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
                        StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanYear);
                        CourseID courseID = new CourseID(courseAcronym, courseName);
                        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID,studyPlanID);
                        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);
                        Date enrolmentDate = new Date(date);
                        EnrolmentStatus status = new EnrolmentStatus(isActive);

                        controller.enrolStudentInCourseEdition(cceGeneraedid, studentID,courseEditionID, enrolmentDate, status);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\nCourseEditionEnrolment loading time: " + (endTime - startTime) + " ms\n");
    }
}
