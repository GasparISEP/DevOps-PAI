package PAI.initializer;

import PAI.VOs.*;
import PAI.controller.US10_IWantToConfigureDegreeTypesLevelsController;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;

@Component
public class DegreeTypeInitializer {

    public void loadDegreeType(US10_IWantToConfigureDegreeTypesLevelsController _controller, String csvFilePath) {
        System.out.println("DegreeTypeInitializer is running");
        long startTime = System.currentTimeMillis();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                System.out.println("Raw line: '" + line + "'");

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                try {
                    String[] parts = line.split(";");
                    System.out.println("Split parts length: " + parts.length);

                    if (parts.length < 3) {
                        System.out.println("Skipping invalid line: " + line);
                        continue;
                    }

                    int maxEctss = Integer.parseInt(parts[0].trim());
                    String dtnames = parts[1].trim();
                    String degreeTypeID = parts[2].trim();

                    System.out.println("Parsed ECTS: " + maxEctss + ", Name: '" + dtnames + "'");

                    Name dtName = new Name(dtnames);
                    MaxEcts maxEcts = new MaxEcts(maxEctss);
                    DegreeTypeID degreeTypeID1 = new DegreeTypeID(degreeTypeID);

                    System.out.println("Registering DegreeType - Name: " + dtName.getName() + ", ECTS: " + maxEcts.getMaxEcts());
                    _controller.registerDegreeTypeWithUUID(degreeTypeID1,dtName,maxEcts);

                } catch (Exception ex) {
                    System.err.println("Error processing line: " + line);
                    ex.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.err.println("Error reading the file");
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("\nCourseEdition loading time: " + (endTime - startTime) + " ms\n");
    }
}