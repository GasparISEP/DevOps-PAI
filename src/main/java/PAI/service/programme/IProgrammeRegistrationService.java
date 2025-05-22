package PAI.service.programme;

import PAI.dto.Programme.ProgrammeResponseDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;

public interface IProgrammeRegistrationService {

    ProgrammeResponseDTO registerProgramme(ProgrammeVOsDTO programmeVOsDTO) throws Exception;
}
