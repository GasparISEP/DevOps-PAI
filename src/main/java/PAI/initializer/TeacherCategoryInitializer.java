package PAI.initializer;

import PAI.VOs.Name;
import PAI.controller.US01_ConfigureTeacherCategoryController;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
public class TeacherCategoryInitializer {

    public void loadTeacherCategory(US01_ConfigureTeacherCategoryController controller, String csvFilePath) {

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                Name name = new Name (line.trim());
                controller.configureTeacherCategory(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}