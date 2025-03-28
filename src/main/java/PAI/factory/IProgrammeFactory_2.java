package PAI.factory;
import PAI.VOs.*;
import PAI.domain.*;

public interface IProgrammeFactory_2 {
    Programme_2 registerProgramme (NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeType_ID degreeTypeID, Department department, TeacherID programmeDirectorID);
}