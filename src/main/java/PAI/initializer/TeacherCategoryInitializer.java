package PAI.initializer;

import PAI.controller.US01_ConfigureTeacherCategoryController;
import PAI.controller.US02_ConfigureAccessMethodController;
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
                String name = line.trim();
                if (!name.isEmpty()) {
                    controller.configureTeacherCategory(name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}