package PAI.initializer;

import PAI.VOs.*;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.service.programmeEdition.IProgrammeEditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.UUID;

@Component
public class ProgrammeEditionInitializer {
    @Autowired
    IProgrammeEditionService service;

    @PostConstruct
    public void init() {
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

                if (fields.length == 3) {
                    try {
                        Acronym programmeAcronym = new Acronym(fields[0].trim());
                        UUID schoolYear = UUID.fromString(fields[1].trim());

                        SchoolYearID schoolYearID = new SchoolYearID(schoolYear);
                        ProgrammeID programmeID = new ProgrammeID(programmeAcronym);

                        ProgrammeEdition programmeEdition = service.createProgrammeEdition(programmeID, schoolYearID);
                        service.saveProgrammeEdition(programmeEdition);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\nProgrammeEdition loading time: " + (endTime - startTime) + " ms\n");
    }
}
