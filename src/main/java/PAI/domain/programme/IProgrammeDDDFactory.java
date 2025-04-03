package PAI.domain.programme;
import PAI.VOs.*;
import PAI.domain.*;

public interface IProgrammeDDDFactory {
    ProgrammeDDD registerProgramme (NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID);
}