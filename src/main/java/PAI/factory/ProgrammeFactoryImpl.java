package PAI.factory;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeFactoryImpl implements IProgrammeFactory {
    public Programme registerProgramme (NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) {
        ProgrammeID programmeID = new ProgrammeID(name, acronym);
        return new Programme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID, programmeID);
    }
}
