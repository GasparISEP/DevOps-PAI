package PAI.initializer;

import PAI.VOs.*;
import PAI.controller.US18_CreateProgrammeEditionForCurrentSchoolYearController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

@Configuration
@Profile("programme-edition")
public class ProgrammeEditionInitializer {
    @Bean
    public CommandLineRunner loadProgrammeEditions(US18_CreateProgrammeEditionForCurrentSchoolYearController controller) {
        return args -> {

            long startTime = System.currentTimeMillis();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            Objects.requireNonNull(getClass().getResourceAsStream("/ProgrammeEdition.csv"))
                    )
            )) {

                String line;
                boolean isFirstLine = true;

                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }

                    line = line.replace("\uFEFF", "");
                    String[] fields = line.split(",");

                    if (fields.length == 2) {
                        try {
                            Acronym programmeAcronym = new Acronym(fields[0].trim());
                            NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars(fields[1].trim());


                            SchoolYearID schoolYearID = controller.getCurrentSchoolYear();
                            ProgrammeID programmeID = new ProgrammeID(programmeName, programmeAcronym);
                            controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeID, schoolYearID);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("\nProgrammeEditionLoading time: " + (endTime - startTime) + " ms\n");
        };
    }

}
