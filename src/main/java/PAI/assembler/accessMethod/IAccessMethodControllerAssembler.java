package PAI.assembler.accessMethod;

import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;

public interface IAccessMethodControllerAssembler {

    RegisterAccessMethodCommand toCommand(AccessMethodRequestDTO dto);
    AccessMethodResponseDTO toResponseDto(AccessMethodServiceDTO amServiceDTO);

}
