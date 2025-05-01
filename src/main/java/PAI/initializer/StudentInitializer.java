package PAI.initializer;

import PAI.controller.US08_IWantToRegisterAStudentInTheSystemController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.FileReader;

@Configuration
@Profile("student")
public class StudentInitializer {

    @Bean
    public CommandLineRunner loadDataRegisterStudent(US08_IWantToRegisterAStudentInTheSystemController controller) {
        return (args) -> {
            loadStudents(controller);
        };
    }

    private void loadStudents(US08_IWantToRegisterAStudentInTheSystemController controller) {
        String csvFile = "src/main/resources/Student_Data.csv";

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

                int StudentNumber = Integer.parseInt(fields[0]);
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

                controller.registerStudent(StudentNumber, name, nif, countryNif, countryCode, phoneNumber, email, street, postalCode, location, country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
