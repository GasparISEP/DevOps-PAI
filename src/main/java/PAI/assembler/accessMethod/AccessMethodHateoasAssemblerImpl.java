package PAI.assembler.accessMethod;

import PAI.controllerRest.AccessMethodRestController;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static PAI.utils.ValidationUtils.validateNotNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AccessMethodHateoasAssemblerImpl implements
        RepresentationModelAssembler<AccessMethodResponseDTO, EntityModel<AccessMethodResponseDTO>>,
        IAccessMethodHateoasAssembler {

    @Override
    public EntityModel<AccessMethodResponseDTO> toModel(AccessMethodResponseDTO dto) {
        validateNotNull(dto, "AccessMethodResponseDTO");

        return EntityModel.of(dto,
                linkTo(methodOn(AccessMethodRestController.class)
                        .getAccessMethodById(dto.id()))
                        .withSelfRel(),

                linkTo(methodOn(AccessMethodRestController.class)
                        .getAllAccessMethods())
                        .withRel("collection")
        );
    }

    @Override
    public CollectionModel<EntityModel<AccessMethodResponseDTO>> toCollectionModel(List<AccessMethodResponseDTO> dtos) {
        List<EntityModel<AccessMethodResponseDTO>> models = dtos.stream()
                .map(this::toModel)
                .toList();

        return CollectionModel.of(models,
                linkTo(methodOn(AccessMethodRestController.class).getAllAccessMethods()).withSelfRel());
    }
}

