package PAI.initializer;

import PAI.VOs.*;
import PAI.controller.US10_IWantToConfigureDegreeTypesLevelsController;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class DegreeTypeInitializer {
    @Autowired
    US10_IWantToConfigureDegreeTypesLevelsController _controller;
    @PostConstruct
    public void init() {
        System.out.println("DegreeTypeInitializer is running");
        try (InputStream is = getClass().getResourceAsStream("/DegreeTypeData.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                try {
                    String[] parts = line.split(";");
                    if (parts.length < 2) {
                        System.out.println("Skipping invalid line: " + line);
                        continue;
                    }
                    int maxEctss = Integer.parseInt(parts[0].trim());
                    String dtnames = parts[1].trim();
                    Name dtName = new Name(dtnames);
                    MaxEcts maxEcts = new MaxEcts(maxEctss);
                    _controller.registerDegreeType(dtName,maxEcts);

                } catch (Exception ex) {
                    System.err.println("Error processing line: " + line);
                    ex.printStackTrace();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}