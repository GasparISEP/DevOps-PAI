package PAI.initializer;

import PAI.VOs.*;
import PAI.controller.US02_ConfigureAccessMethodController;
import PAI.controller.US11_RegisterProgrammeInTheSystemController;
import PAI.domain.degreeType.DegreeType;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
@Component
public class ProgrammeInitializer {
    @Autowired
    private US11_RegisterProgrammeInTheSystemController _controller;

    @PostConstruct
    public void init() {
        try (InputStream is = getClass().getResourceAsStream("/ProgrammeData.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;  // skip header
                }

                String[] parts = line.split(";");
                if (parts.length < 7) continue; // skip invalid lines

                String name = parts[0].trim();
                String acronym = parts[1].trim();
                int qtyEcts = Integer.parseInt(parts[2].trim());
                int qtyOfSemesters = Integer.parseInt(parts[3].trim());
                DegreeTypeID degreeTypeID = new DegreeTypeID(parts[4].trim());
                DepartmentID departmentID = new DepartmentID(new DepartmentAcronym(parts[5].trim()));
                TeacherID teacherID = new TeacherID(new TeacherAcronym(parts[6].trim()));

                if (!name.isEmpty()) {
                    _controller.registerProgramme(name, acronym, qtyEcts, qtyOfSemesters, degreeTypeID, departmentID, teacherID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}