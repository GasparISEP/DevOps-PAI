package PAI.assembler.accessMethod;

import PAI.dto.accessMethod.AccessMethodResponseDTO;
import org.springframework.hateoas.EntityModel;

public interface IAccessMethodHateoasAssembler {

    EntityModel<AccessMethodResponseDTO> toModel(AccessMethodResponseDTO dto);
}
