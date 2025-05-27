package PAI.assembler.accessMethod;

import PAI.domain.accessMethod.AccessMethod;
import PAI.dto.accessMethod.AccessMethodServiceDTO;

public interface IAccessMethodServiceAssembler {

    AccessMethodServiceDTO toDTO(AccessMethod accessMethod);


}
