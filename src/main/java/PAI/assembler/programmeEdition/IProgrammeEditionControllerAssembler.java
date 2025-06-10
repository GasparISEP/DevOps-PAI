package PAI.assembler.programmeEdition;

import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.*;

public interface IProgrammeEditionControllerAssembler {
    ProgrammeEditionRequestServiceDTO toServiceDTOFromRequestDTO(ProgrammeEditionRequestDTO programmeEditionRequest);
    ProgrammeEditionResponseDTO toResponseDTOFromServiceDTO(ProgrammeEditionResponseServiceDTO responseDTO);
    CountStudentsRequestDto toCountDTO(ProgrammeEdition programmeEdition);
}
