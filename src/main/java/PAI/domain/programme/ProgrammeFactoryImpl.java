package PAI.domain.programme;

import PAI.VOs.*;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeFactoryImpl implements IProgrammeFactory {
    public Programme registerProgramme (NameWithNumbersAndSpecialChars name, Acronym acronym, MaxEcts maxOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) {
        ProgrammeID programmeID = new ProgrammeID(acronym);
        return new Programme(name, acronym, maxOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID, programmeID);
    }

    public Programme reregisterProgramme (NameWithNumbersAndSpecialChars name, Acronym acronym, MaxEcts maxOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID, ProgrammeID id) {
        return new Programme(name, acronym, maxOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID, id);
    }
}
