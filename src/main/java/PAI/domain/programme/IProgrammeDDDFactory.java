package PAI.domain.programme;
import PAI.VOs.*;
import PAI.domain.*;

public interface IProgrammeDDDFactory {
    ProgrammeDDD registerProgramme (NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeType_ID degreeTypeID, Department department, TeacherID programmeDirectorID);
}