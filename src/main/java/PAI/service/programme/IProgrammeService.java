package PAI.service.programme;

import PAI.VOs.*;

public interface IProgrammeService {
    boolean registerProgramme (NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) throws Exception;
    boolean changeProgrammeDirector(ProgrammeID programmeID, TeacherID programmeDirectorID) throws Exception;
}
