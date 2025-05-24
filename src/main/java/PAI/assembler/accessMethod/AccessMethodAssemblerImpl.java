package PAI.assembler.accessMethod;

import PAI.domain.accessMethod.AccessMethod;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AccessMethodAssemblerImpl implements IAccessMethodAssembler {

    @Override
    public RegisterAccessMethodCommand toCommand(AccessMethodRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("AccessMethodRequestDTO cannot be null");
        }
        return new RegisterAccessMethodCommand(dto.name());
    }

    @Override
    public AccessMethodResponseDTO toDto(AccessMethod accessMethod) {
        if (accessMethod == null) {
            throw new IllegalArgumentException("AccessMethod cannot be null");
        }
        return new AccessMethodResponseDTO(
                accessMethod.identity().toString(),
                accessMethod.getAccessMethodName().getnameWithNumbersAndSpecialChars());

    }

}
