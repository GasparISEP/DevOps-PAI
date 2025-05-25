package PAI.service.accessMethod;

import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.accessMethod.AccessMethodResponseDTO;

import java.util.Optional;

public interface IAccessMethodService {

    Optional<AccessMethodResponseDTO> configureAccessMethod(RegisterAccessMethodCommand command);
}
