package PAI.service.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.CountStudentsDto;
import PAI.dto.programmeEdition.ProgrammeEditionServiceDTO;

import java.util.List;
import java.util.Optional;

public interface IProgrammeEditionService {
    ProgrammeEdition createProgrammeEdition(ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception;
    Optional<ProgrammeEdition> saveProgrammeEdition(ProgrammeEdition programmeEdition) throws Exception;
    List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeID) throws Exception;
    List<ProgrammeEdition> findAllProgrammeEditions() throws Exception;
    int countTotalNumberOfStudentsInAProgrammeEdition(CountStudentsDto programmeEditionDTO) throws Exception;
    ProgrammeEditionServiceDTO createProgrammeEditionAndSave(ProgrammeEditionServiceDTO programmeEditionServiceDTO) throws Exception;
    List<ProgrammeEditionID> getProgrammeEditionIDsByProgrammeID(ProgrammeID programmeID);
}
