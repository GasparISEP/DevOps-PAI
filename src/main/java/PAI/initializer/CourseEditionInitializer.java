package PAI.initializer;

import PAI.VOs.*;

import PAI.service.courseEdition.ICreateCourseEditionService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                        Objects.requireNonNull(getResourceAsStream("/CourseEdition.csv"))
                )
        )) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                line = line.replace("\uFEFF", "").trim();
                if (line.isEmpty()) continue;

                String[] fields = line.split(",", -1);

                if (fields.length == 9) {
                    try {
                        // Debug print
                        System.out.println("Processing line: " + line);

                        // Create Value Objects first
                        LocalDate localDate = LocalDate.parse(fields[0].trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        Acronym courseAcronym = new Acronym(fields[1].trim());
                        Name courseName = new Name(fields[2].trim());
                        UUID schoolYearUUID = UUID.fromString(fields[5].trim());
                        Acronym programmeAcronym = new Acronym(fields[6].trim());

                        // Create domain IDs
                        SchoolYearID schoolYearID = new SchoolYearID(schoolYearUUID);

                        // Create ProgrammeID for both edition and programme (they are the same)
                        ProgrammeID programmeID = new ProgrammeID(programmeAcronym);
                        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);

                        // Create CourseID
                        CourseID courseID = new CourseID(courseAcronym, courseName);

                        // Create StudyPlanID with the same programme information
                        StudyPlanID studyPlanID = new StudyPlanID(programmeID, new Date(localDate));

                        // Create CourseInStudyPlanID
                        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);

                        // Call the service with domain IDs
                        _service.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);

                    } catch (Exception e) {
                        System.err.println("Error processing line: " + line);
                        e.printStackTrace();
                    }
                } else {
                    System.err.println("Skipping line with unexpected number of fields (" + fields.length + "): " + line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\nCourseEdition loading time: " + (endTime - startTime) + " ms\n");
    }

    /**
     * Gets an input stream for the resource with the specified name.
     * This method can be overridden for testing purposes.
     *
     * @param resourceName the name of the resource
     * @return an input stream for reading the resource
     */
    protected InputStream getResourceAsStream(String resourceName) {
        return getClass().getResourceAsStream(resourceName);
    }
}
