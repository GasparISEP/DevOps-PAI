package PAI.initializer;
import PAI.VOs.*;
import PAI.service.courseEdition.ICreateCourseEditionService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.UUID;

@Component
public class CourseEditionInitializer {
    @Autowired
    ICreateCourseEditionService _service;

    @PostConstruct
    public void init() {
        long startTime = System.currentTimeMillis();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(getClass().getResourceAsStream("/CourseEdition.csv"))
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
                        Date studyPlanDate = new Date(fields[0].trim());
                        Acronym courseAcronym = new Acronym(fields[1].trim());
                        Name courseName = new Name(fields[2].trim());
                        UUID schoolYear = UUID.fromString(fields[3].trim());
                        Acronym programmeAcronym = new Acronym(fields[4].trim());
                        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars(fields[5].trim());

                        SchoolYearID schoolYearID = new SchoolYearID(schoolYear);
                        ProgrammeID programmeID = new ProgrammeID(programmeName, programmeAcronym);
                        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
                        StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanDate);
                        CourseID courseID = new CourseID(courseAcronym, courseName);
                        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID,studyPlanID);

                        _service.createAndSaveCourseEdition(courseInStudyPlanID,programmeEditionID);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\nCourseEdition loading time: " + (endTime - startTime) + " ms\n");
    }
}
