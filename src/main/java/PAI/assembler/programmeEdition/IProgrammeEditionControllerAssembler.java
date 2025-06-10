package PAI.assembler.programmeEdition;

import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.programmeEdition.*;

import java.util.List;

public interface IProgrammeEditionControllerAssembler {
    ProgrammeEditionRequestServiceDTO toServiceDTOFromRequestDTO(ProgrammeEditionRequestDTO programmeEditionRequest);
    ProgrammeEditionResponseDTO toResponseDTOFromServiceDTO(ProgrammeEditionResponseServiceDTO responseDTO);
    List<ProgrammeEditionResponseDTO> toResponseDTOList (ProgrammeEditionResponseServiceDTO responseDTO);
}
