package PAI.initializer;

import PAI.VOs.*;
import PAI.controller.US16_EnrolAStudentInACourseEditionController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Configuration
@Profile("course-edition-enrolment")
public class CourseEditionEnrolmentInitializer {

    @Bean
    public CommandLineRunner loadCourseEditionEnrolments(US16_EnrolAStudentInACourseEditionController controller) {
        return args -> {
            long startTime = System.currentTimeMillis();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            Objects.requireNonNull(getClass().getResourceAsStream("/CourseEditionEnrolment.csv"))
                    )
            )) {

                String line;
                boolean isFirstLine = true;

                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }

                    line = line.replace("\uFEFF", "");
                    String[] fields = line.split(",");

                    if (fields.length == 6) {
                        try {
                            int studentNum = Integer.parseInt(fields[0].trim());
                           Acronym programmeAcronym = new Acronym(fields[1].trim());
                            UUID schoolYear = UUID.fromString(fields[2].trim());
                            int studyPlanStartYear = Integer.parseInt(fields[3].trim());
                            Acronym courseAcronym = new Acronym(fields[4].trim());
                            Name courseName = new Name(fields[5].trim());

                            SchoolYearID schoolYearID = new SchoolYearID(schoolYear);
                            Date studyPlanYear = new Date(LocalDate.of(studyPlanStartYear,1,1));
                            StudentID studentID = new StudentID(studentNum);
                            ProgrammeID programmeID = new ProgrammeID(programmeAcronym);
                            ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
                            StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanYear);
                            CourseID courseID = new CourseID(courseAcronym, courseName);
                            CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID,studyPlanID);
                            CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);

                            controller.enrolStudentInCourseEdition(studentID,courseEditionID);

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
        };
    }
}
