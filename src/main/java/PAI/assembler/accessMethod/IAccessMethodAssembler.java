package PAI.assembler.accessMethod;

import PAI.domain.accessMethod.AccessMethod;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;

public interface IAccessMethodAssembler {

    RegisterAccessMethodCommand toCommand(AccessMethodRequestDTO dto);
    AccessMethodServiceDTO toDto(AccessMethod accessMethod);
    AccessMethodResponseDTO toResponseDto(AccessMethodServiceDTO amServiceDTO);

}
