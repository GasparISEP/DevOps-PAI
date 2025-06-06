package PAI.assembler.accessMethod;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

import static PAI.utils.ValidationUtils.validateNotNull;

@Component
public class AccessMethodControllerAssemblerImpl implements IAccessMethodControllerAssembler {

    @Override
    public RegisterAccessMethodCommand toCommand(AccessMethodRequestDTO dto) {
        validateNotNull(dto, "AccessMethodRequestDTO");
        NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars(dto.name());
        return new RegisterAccessMethodCommand(nameVO);
    }

    @Override
    public AccessMethodResponseDTO toResponseDto(AccessMethodServiceDTO amServiceDTO) {
        validateNotNull(amServiceDTO, "AccessMethodServiceDTO");

        return new AccessMethodResponseDTO(
                amServiceDTO.id(),
                amServiceDTO.name());
    }

    @Override
    public List<AccessMethodResponseDTO> toResponseDtoList(List<AccessMethodServiceDTO> serviceDTOs) {
        validateNotNull(serviceDTOs, "AccessMethodServiceDTO List");

        return serviceDTOs.stream()
                .map(this::toResponseDto)
                .toList();
    }

}
