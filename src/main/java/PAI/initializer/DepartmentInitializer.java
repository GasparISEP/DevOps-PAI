package PAI.initializer;

import PAI.VOs.DepartmentAcronym;

import PAI.VOs.Name;
import PAI.controller.US05_DepartmentRegistryController;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.*;

@Configuration
public class DepartmentInitializer {

    public void loadDepartment(US05_DepartmentRegistryController controller, String csvFilePath) {

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
                if (fields.length < 2) continue;

                DepartmentAcronym acronym = new DepartmentAcronym(fields[0].trim());
                Name name = new Name(fields[1].trim());

                controller.registerDepartment(acronym, name);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("\nDepartment data loading time: " + (endTime - startTime) + " ms\n");

        } catch (Exception e) {
            throw new RuntimeException("Failed to load departments from CSV", e);
        }
    }

}