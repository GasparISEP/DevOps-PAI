package PAI.service.programme;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.dto.Programme.ProgrammeDTO;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;

import java.util.List;
import java.util.Optional;

public interface IProgrammeService {
    boolean changeProgrammeDirector(ProgrammeID programmeID, TeacherID programmeDirectorID) throws Exception;
    List<ProgrammeID> findProgrammeByDepartment(DepartmentID id);
    List<Programme> getProgrammesByDegreeTypeID(DegreeTypeID id) throws Exception;
    Optional<Programme> getProgrammeByName(NameWithNumbersAndSpecialChars name);
    Optional<Programme> getProgrammeByAcronym(Acronym acronym);
    List<ProgrammeID> getAllProgrammeIDs();
    Iterable<Programme> findAll();
    Optional <ProgrammeDTO> getProgrammeByID(ProgrammeID id);
    Programme registerProgramme(ProgrammeVOsDTO programmeVOsDTO) throws Exception;
    List<ProgrammeIDDTO> getAllProgrammeIDDTOs();
    List<ProgrammeIDDTO> getProgrammeIDDTOsByDegreeTypeID(DegreeTypeID id);
    Iterable<ProgrammeDTO> getAllProgrammes();
    List<Programme> getProgrammesByProgrammeIDs (List<ProgrammeID> list);
    Optional<TeacherID> getProgrammeDirectorByProgrammeID(ProgrammeID programmeID);
}

