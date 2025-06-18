package PAI.initializer;

import PAI.VOs.*;
import PAI.controller.US19_CreateCourseEditionController;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.schoolYear.SchoolYear;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Configuration
public class CourseEditionInitializer {

    public void loadCourseEdition(US19_CreateCourseEditionController controller, ISchoolYearRepository schoolYearRepository, String csvFilePath) {
        long startTime = System.currentTimeMillis();
        List<SchoolYearID> schoolYearsList = schoolYearRepository.getAllSchoolYearsIDs();
        SchoolYearID schoolYearID = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

            String line;
            boolean isFirstLine = true;

            if (schoolYearsList.isEmpty()) {
                throw new NullPointerException("Database has no school year");
            }

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                line = line.replace("\uFEFF", "").trim();
                if (line.isEmpty()) continue;

                String[] fields = line.split(",");

                try {
                    if (schoolYearsList.isEmpty()){
                        new NullPointerException("School Year not found");
                    }
                    System.out.println("Processing line: " + line);

                    LocalDate localDate = LocalDate.parse(fields[0].trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    Acronym courseAcronym = new Acronym(fields[1].trim());
                    Name courseName = new Name(fields[2].trim());
                    Acronym programmeAcronym = new Acronym(fields[3].trim());
                    ProgrammeID programmeID = new ProgrammeID(programmeAcronym);
                    CourseID courseID = new CourseID(courseAcronym, courseName);
                    StudyPlanID studyPlanID = new StudyPlanID(programmeID, new Date(localDate));
                    CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);

                    for (SchoolYearID schoolYearIdItem : schoolYearsList) {
                        Optional<SchoolYear> schoolYearOpt = schoolYearRepository.findBySchoolYearID(schoolYearIdItem);
                        if (schoolYearOpt.isEmpty()) {
                            System.err.println("No School Year found for that id");
                            continue;
                        }
                        SchoolYear schoolYear = schoolYearOpt.get();
                        LocalDate startDate = schoolYear.getStartDate().getLocalDate();
                        LocalDate endDate = schoolYear.getEndDate().getLocalDate();
                        if (!localDate.isBefore(startDate) && !localDate.isAfter(endDate)) {
                            schoolYearID = new SchoolYearID(schoolYearIdItem.getSchoolYearID());
                            ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
                            controller.createCourseEdition(courseInStudyPlanID, programmeEditionID);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error processing line: " + line);
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.err.println("Failed to read CSV file: " + csvFilePath);
            e.printStackTrace();
        }

        /**
         * Gets an input stream for the resource with the specified name.
         * This method can be overridden for testing purposes.
         *
         * @param resourceName the name of the resource
         * @return an input stream for reading the resource
         */
        long endTime = System.currentTimeMillis();
        System.out.println("\nCourseEdition loading time: " + (endTime - startTime) + " ms\n");
    }
}