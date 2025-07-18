package PAI.initializer;

import PAI.VOs.*;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

@Component
public class CourseInStudyPlanInitializer {

    public void loadCourseInStudyPlan(ICourseInStudyPlanService courseInStudyPlanService, String csvFilePath) {

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

            String line = reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",", -1);
                if (values.length < 10) continue;

                int duration = Integer.parseInt(values[0].trim());
                int curricularYear = Integer.parseInt(values[1].trim());
                double ects = Double.parseDouble(values[2].trim());
                LocalDate localDate = LocalDate.parse(values[3].trim());
                int semester = Integer.parseInt(values[4].trim());
                String courseAcronym = values[6].trim();
                String courseName = values[7].trim();
                String programmeAcronym = values[8].trim();

                Acronym acronym = new Acronym(courseAcronym);
                Name name = new Name(courseName);
                Acronym prgmAcronym = new Acronym(programmeAcronym);
                Date implementationDate = new Date(localDate);
                ProgrammeID programmeID = new ProgrammeID(prgmAcronym);

                CourseID courseID = new CourseID(acronym, name);
                Semester semester1 = new Semester(semester);
                CurricularYear curricularYear1 = new CurricularYear(curricularYear);
                StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);
                DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(duration);
                CourseQuantityCreditsEcts quantityCreditsEcts = new CourseQuantityCreditsEcts(ects);

                courseInStudyPlanService.createCourseInStudyPlan(semester1, curricularYear1, courseID, studyPlanID, durationCourseInCurricularYear, quantityCreditsEcts, programmeID);
            }

            System.out.println("CourseInStudyPlan data loaded successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}