package PAI.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammeList {
    private final ProgrammeFactory _programmeFactory;
    private List<Programme> programmeList;

    public ProgrammeList(ProgrammeFactory programmeFactory) {
        _programmeFactory = programmeFactory;
        programmeList = new ArrayList<>();
    }

    public boolean registerProgramme(String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector) throws Exception {

        Programme programme = _programmeFactory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, programmeDirector);

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

    public List<Course> getCourseList(Programme programme) {
        return programme.getCourseList();
    }

    public Optional<Programme> getProgrammeByName(String name) {
        for (Programme programme : programmeList) {
            if (programme.hasThisProgrammeName(name)) {
                return Optional.of(programme);
            }
        }
        return Optional.empty();
    }

    public Programme getProgrammeByAcronym(String acronym) {
        for (Programme programme : programmeList) {
            if (programme.getAcronym().equals(acronym)){
                return programme;
            }
        }
        return null;
    }

    public List<String>  getAllProgrammeNames() {

        List<String> list = new ArrayList<>();
        for (Programme programme : programmeList) {
            list.add(programme.getProgrammeName());
        }
        return list;
    }
}
