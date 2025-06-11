package PAI.service.programmeEdition;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.RequestServiceDto;
import PAI.dto.programmeEdition.ProgrammeEditionRequestServiceDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseServiceDTO;

import java.util.List;
import java.util.Optional;

public interface IProgrammeEditionService {
    ProgrammeEdition createProgrammeEdition(ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception;
    Optional<ProgrammeEdition> saveProgrammeEdition(ProgrammeEdition programmeEdition) throws Exception;
    List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeID);
    List<ProgrammeEditionResponseServiceDTO> findAllProgrammeEditions();
    int countTotalNumberOfStudentsInAProgrammeEdition(RequestServiceDto programmeEditionDTO) throws Exception;
    ProgrammeEditionResponseServiceDTO createProgrammeEditionAndSave(ProgrammeEditionRequestServiceDTO programmeEditionRequestServiceDTO) throws Exception;
    List<ProgrammeEditionResponseServiceDTO> getProgrammeEditionIDsByProgrammeID(ProgrammeEditionRequestServiceDTO requestDTO);
}
