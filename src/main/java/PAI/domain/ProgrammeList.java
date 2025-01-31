package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeList {
    private List<Programme> programmeList;

    public ProgrammeList() {
        programmeList = new ArrayList<>();
    }


    public boolean registerProgramme(String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector) throws Exception {

        Programme programme = new Programme (name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, programmeDirector);

        if (programmeList.contains(programme))
            return false;

        programmeList.add(programme);
        return true;
    }

    // Change ProgrammeDirector
    public boolean changeProgrammeDirector(Programme programme, Teacher newDirector) throws Exception {
        if (newDirector == null) {
            return false;
        }

        programme.newProgrammeDirector(newDirector);
        return true;
    }

    public List<Programme> getAllProgrammes() {
        return new ArrayList<>(programmeList);
    }
    
}
