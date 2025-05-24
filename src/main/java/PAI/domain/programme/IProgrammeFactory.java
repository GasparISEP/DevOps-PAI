package PAI.domain.programme;
import PAI.VOs.*;

public interface IProgrammeFactory {
    Programme registerProgramme (NameWithNumbersAndSpecialChars name, Acronym acronym, MaxEcts maxOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID);
    Programme reregisterProgramme(NameWithNumbersAndSpecialChars name, Acronym acronym, MaxEcts maxOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID, ProgrammeID id);
}