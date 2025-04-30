package PAI.service.programme;

import PAI.VOs.*;
import PAI.domain.programme.Programme;

import java.util.List;
import java.util.Optional;

public interface IProgrammeService {
    boolean registerProgramme (NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) throws Exception;
    boolean changeProgrammeDirector(ProgrammeID programmeID, TeacherID programmeDirectorID) throws Exception;
    List<ProgrammeID> findProgrammeByDepartment(DepartmentID id);
    List<Programme> getProgrammesByDegreeTypeID(DegreeTypeID id) throws Exception;
    Optional<Programme> getProgrammeByName(NameWithNumbersAndSpecialChars name);
    Programme getProgrammeByAcronym(Acronym acronym);
    List<ProgrammeID> getAllProgrammeIDs();
    Iterable<Programme> findAll();
    Optional <Programme> getProgrammeByID(ProgrammeID id);
}

