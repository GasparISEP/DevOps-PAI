package PAI.initializer;

import PAI.VOs.DepartmentAcronym;

import PAI.VOs.Name;
import PAI.controller.US05_DepartmentRegistryController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;

@Configuration
public class DepartmentInitializer {

    private final US05_DepartmentRegistryController controller;

    public DepartmentInitializer(US05_DepartmentRegistryController controller) {
        this.controller = controller;
    }

    @Bean
    public CommandLineRunner loadDataRunner() {
        return args -> init();
    }

    public void init() {

        long startTime = System.currentTimeMillis();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Department.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            boolean isFirstLine = true;


            while ((line = br.readLine()) != null) {
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