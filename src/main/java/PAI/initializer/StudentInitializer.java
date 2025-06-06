package PAI.initializer;

import PAI.controller.US08_IWantToRegisterAStudentInTheSystemController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;

@Configuration
public class StudentInitializer {

    @Bean
    public CommandLineRunner loadDataRegisterStudent(US08_IWantToRegisterAStudentInTheSystemController controller) {
        return (args) -> {
            loadStudents(controller);
        };
    }

    private void loadStudents(US08_IWantToRegisterAStudentInTheSystemController controller) {
        String csvFile = "src/main/resources/Student_Data.csv";

        long startTime = System.currentTimeMillis();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                line = line.replace("\uFEFF", "");
                String[] fields = line.split(";");

                if (fields.length < 11) {
                    System.err.println("⚠️ Skipping malformed line: " + line);
                    continue;
                }


                String name = fields[1];
                String nif = fields[2];
                String countryNif = fields[3];
                String countryCode = fields[4];
                String phoneNumber = fields[5];
                String email = fields[6];
                String street = fields[7];
                String postalCode = fields[8];
                String location = fields[9];
                String country = fields[10];



                try {
                    controller.registerStudent(name, nif, countryNif, countryCode, phoneNumber, email, street, postalCode, location, country);
                    System.out.println("✅ Student registered: " + name);
                } catch (Exception e) {
                    System.err.println("❌ Failed to register student: " + name);
                    e.printStackTrace(); // 🔍 now you'll see what failed
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("\nStudent data loading time: " + duration + " ms\n");
    }

}
