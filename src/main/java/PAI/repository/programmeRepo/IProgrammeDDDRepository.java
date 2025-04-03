package PAI.repository.programmeRepo;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.Department;
import PAI.domain.Programme;
import PAI.domain.programme.ProgrammeDDD;

import java.util.List;

public interface IProgrammeDDDRepository extends IRepository <ProgrammeID, ProgrammeDDD> {

    boolean registerProgramme(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) throws Exception;

    List<ProgrammeDDD> getProgrammesByDegreeTypeID(DegreeTypeID degreeTypeID) throws Exception;
}
