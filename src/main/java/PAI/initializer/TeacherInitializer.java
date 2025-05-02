package PAI.initializer;

import PAI.controller.US13_RegisterTeacherAndRelevantDataController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.FileReader;

@Configuration
@Profile("teacher")
public class TeacherInitializer {

    @Bean
    public CommandLineRunner loadDataRegisterTeacher(US13_RegisterTeacherAndRelevantDataController controller) {
        return (args) -> {
            loadTeachers(controller);
        };
    }

    private void loadTeachers(US13_RegisterTeacherAndRelevantDataController controller) {
        String csvFile = "src/main/resources/Teacher_Data.csv";

        long startTime = System.currentTimeMillis();

        try (BufferedReader br2 = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br2.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                line = line.replace("\uFEFF", "");
                String[] fields = line.split(";");

                String acronym = fields[0];
                String name = fields[1];
                String email = fields[2];
                String nif = fields[3];
                String phoneNumber = fields[4];
                String countryCode = fields[11];
                String academicBackground = fields[5];
                String street = fields[6];
                String postalCode = fields[7];
                String location = fields[8];
                String country = fields[9];
                String departmentAcronym = fields[10];
                String date = fields[12];
                String teacherCategoryID = fields[13];
                int workingPercentage = Integer.parseInt(fields[14]);

                controller.registerTeacher(acronym, name, email, nif, phoneNumber, academicBackground, street,
                        postalCode, location, country, departmentAcronym, date, teacherCategoryID, workingPercentage, countryCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("\nTeacher data loading time: " + duration + " ms\n");
    }
}
