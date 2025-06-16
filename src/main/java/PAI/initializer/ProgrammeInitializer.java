package PAI.initializer;

import PAI.VOs.*;
import PAI.controller.US11_RegisterProgrammeInTheSystemController;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ProgrammeInitializer {

    public void loadProgramme(US11_RegisterProgrammeInTheSystemController controller, IDegreeTypeRepository degreeTypeRepository, String csvFilePath) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                try {
                    String[] parts = line.split(";");
                    if (parts.length < 7) {
                        System.out.println("Skipping invalid line: " + line);
                        continue;
                    }

                    String name = parts[0].trim();
                    String acronym = parts[1].trim();
                    int qtyEcts = Integer.parseInt(parts[2].trim());
                    int qtyOfSemesters = Integer.parseInt(parts[3].trim());
                    DegreeTypeID id = null;
                    Iterable<DegreeType> degreeTypeList = degreeTypeRepository.findAll();
                    for (DegreeType degreeType : degreeTypeList) {
                        String degreeTypeName = parts[4];
                        if (degreeType.getName().getName().equals(degreeTypeName.trim())) {
                            id = degreeType.getId();
                            break;
                        }
                    }
                    DepartmentID departmentID = new DepartmentID(new DepartmentAcronym(parts[5].trim()));
                    TeacherID teacherID = new TeacherID(new TeacherAcronym(parts[6].trim()));

                    controller.registerProgramme(name, acronym, qtyEcts, qtyOfSemesters, id, departmentID, teacherID);

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