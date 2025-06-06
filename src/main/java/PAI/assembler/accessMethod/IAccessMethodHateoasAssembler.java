package PAI.assembler.accessMethod;

import PAI.dto.accessMethod.AccessMethodResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;

public interface IAccessMethodHateoasAssembler {

    EntityModel<AccessMethodResponseDTO> toModel(AccessMethodResponseDTO dto);
    CollectionModel<EntityModel<AccessMethodResponseDTO>> toCollectionModel(List<AccessMethodResponseDTO> dtos);


}
