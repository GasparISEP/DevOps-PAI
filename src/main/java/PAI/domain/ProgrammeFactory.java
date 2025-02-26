package PAI.domain;

public class ProgrammeFactory {
    public Programme registerProgramme (String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector) throws Exception{
        return new Programme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, programmeDirector);
    }
}
