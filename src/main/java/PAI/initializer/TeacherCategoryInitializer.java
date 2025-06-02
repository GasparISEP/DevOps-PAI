package PAI.initializer;

import PAI.VOs.Name;
import PAI.controller.US01_ConfigureTeacherCategoryController;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class TeacherCategoryInitializer {

    @Autowired
    private US01_ConfigureTeacherCategoryController controller;

    @PostConstruct
    public void init() {
        try (InputStream is = getClass().getResourceAsStream("/TeacherCategory.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

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