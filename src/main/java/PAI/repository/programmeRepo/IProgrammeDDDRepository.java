package PAI.repository.programmeRepo;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.Department;
import PAI.domain.programme.ProgrammeDDD;

public interface IProgrammeDDDRepository extends IRepository <ProgrammeID, ProgrammeDDD> {

    boolean registerProgramme(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, Department department, TeacherID programmeDirectorID) throws Exception;

}
