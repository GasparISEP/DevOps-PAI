package PAI.assembler.accessMethod;

import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;

import java.util.List;

public interface IAccessMethodControllerAssembler {

    RegisterAccessMethodCommand toCommand(AccessMethodRequestDTO dto);
    AccessMethodResponseDTO toResponseDto(AccessMethodServiceDTO amServiceDTO);
    List<AccessMethodResponseDTO> toResponseDtoList(List<AccessMethodServiceDTO> serviceDTOs);

}
