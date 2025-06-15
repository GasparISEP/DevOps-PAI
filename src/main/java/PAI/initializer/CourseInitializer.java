package PAI.initializer;

import PAI.VOs.Acronym;
import PAI.VOs.Name;
import PAI.service.course.ICourseService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class CourseInitializer {

    public void loadCourse(ICourseService courseService, String csvFilePath) {

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

            // Skip header
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";", -1);

                String courseName = values[0].trim();
                String courseAcronym = values[1].trim();

                Name nameCourse = new Name(courseName);
                Acronym acronymCourse = new Acronym(courseAcronym);

                courseService.createAndSaveCourse(nameCourse, acronymCourse);
            }

            System.out.println("Courses loaded successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
