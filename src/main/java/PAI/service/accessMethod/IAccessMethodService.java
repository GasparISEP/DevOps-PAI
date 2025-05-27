package PAI.service.accessMethod;

import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import PAI.utils.ServiceResponse;

import java.util.Optional;

public interface IAccessMethodService {

    AccessMethodServiceDTO configureAccessMethod(RegisterAccessMethodCommand command);
}
