package PAI.factory;
import PAI.VOs.*;
import PAI.domain.programme.Programme;

public interface IProgrammeFactory {
    Programme registerProgramme (NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID);
}