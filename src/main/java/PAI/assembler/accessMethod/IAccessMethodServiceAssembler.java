package PAI.assembler.accessMethod;

import PAI.domain.accessMethod.AccessMethod;
import PAI.dto.accessMethod.AccessMethodServiceDTO;

import java.util.List;

public interface IAccessMethodServiceAssembler {

    AccessMethodServiceDTO toDTO(AccessMethod accessMethod);

    List<AccessMethodServiceDTO> toDTOList(List<AccessMethod> accessMethods);
}
