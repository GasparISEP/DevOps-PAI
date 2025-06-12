package PAI.initializer;

import PAI.VOs.*;
import PAI.controller.US10_IWantToConfigureDegreeTypesLevelsController;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

@Component
public class DegreeTypeInitializer {

    @Autowired
    US10_IWantToConfigureDegreeTypesLevelsController _controller;

    @PostConstruct
    public void init() {
        System.out.println("DegreeTypeInitializer is running");

        try (InputStream is = getClassResourceAsStream("/DegreeTypeData.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

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
    }

    public InputStream getClassResourceAsStream(String path) {
        return getClass().getResourceAsStream(path);
    }
}