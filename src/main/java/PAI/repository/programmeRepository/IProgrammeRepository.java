package PAI.repository.programmeRepository;

import PAI.VOs.*;
import PAI.ddd.IRepository;

import PAI.domain.programme.Programme;

import java.util.List;

import java.util.Optional;


public interface IProgrammeRepository extends IRepository <ProgrammeID, Programme> {

    boolean registerProgramme(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) throws Exception;

    List<ProgrammeID> findProgrammeByDepartment(DepartmentID departmentID);

    List<Programme> getProgrammesByDegreeTypeID(DegreeTypeID degreeTypeID) throws Exception;

    Optional<ProgrammeID> findProgrammeIdByProgramme(Programme programme) throws Exception;

    boolean changeProgrammeDirector(ProgrammeID programmeID, TeacherID newDirectorID) throws Exception;

    List<Programme> getAllProgrammes();

    Optional<Programme> getProgrammeByName(NameWithNumbersAndSpecialChars name);

    Programme getProgrammeByAcronym(Acronym acronym);

    List<NameWithNumbersAndSpecialChars> getAllProgrammeNames();

    List<ProgrammeID> getAllProgrammesIDs();
}
