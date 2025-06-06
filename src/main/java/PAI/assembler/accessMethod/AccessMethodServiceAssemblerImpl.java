package PAI.assembler.accessMethod;

import PAI.domain.accessMethod.AccessMethod;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import org.springframework.stereotype.Component;

import java.util.List;

import static PAI.utils.ValidationUtils.validateNotNull;

@Component
public class AccessMethodServiceAssemblerImpl implements IAccessMethodServiceAssembler{


    @Override
    public AccessMethodServiceDTO toDTO(AccessMethod accessMethod) {
        validateNotNull(accessMethod, "AccessMethod");

        return new AccessMethodServiceDTO(
                accessMethod.identity().toString(),
                accessMethod.getAccessMethodName().getNameWithNumbersAndSpecialChars());
    }

    @Override
    public List<AccessMethodServiceDTO> toDTOList(List<AccessMethod> accessMethods) {
        validateNotNull(accessMethods, "AccessMethods List");

        return accessMethods.stream()
                .map(this::toDTO)
                .toList();
    }
}
