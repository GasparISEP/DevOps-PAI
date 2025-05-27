package PAI.assembler.accessMethod;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import org.springframework.stereotype.Component;

import static PAI.utils.ValidationUtils.validateNotNull;

@Component
public class AccessMethodAssemblerImpl implements IAccessMethodAssembler {

    @Override
    public RegisterAccessMethodCommand toCommand(AccessMethodRequestDTO dto) {
        validateNotNull(dto, "AccessMethodRequestDTO");
        NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars(dto.name());
        return new RegisterAccessMethodCommand(nameVO);
    }

    @Override
    public AccessMethodServiceDTO toDto(AccessMethod accessMethod) {
        validateNotNull(accessMethod, "AccessMethod");

        return new AccessMethodServiceDTO(
                accessMethod.identity().toString(),
                accessMethod.getAccessMethodName().getnameWithNumbersAndSpecialChars());

    }


    @Override
    public AccessMethodResponseDTO toResponseDto(AccessMethodServiceDTO amServiceDTO) {
        validateNotNull(amServiceDTO, "AccessMethodServiceDTO");

        return new AccessMethodResponseDTO(
                amServiceDTO.id(),
                amServiceDTO.name());
    }

}
