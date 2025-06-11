package PAI.assembler.degreeType;

import PAI.controllerRest.DegreeTypeRestController;
import PAI.dto.degreeType.DegreeTypeDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DegreeTypeHATEOASAssembler implements RepresentationModelAssembler<DegreeTypeDTO, EntityModel<DegreeTypeDTO>>, IDegreeTypeHATEOASAssembler {

    @Override
    public EntityModel<DegreeTypeDTO> toModel(DegreeTypeDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(DegreeTypeRestController.class).getDegreeTypeById(dto.id())).withSelfRel(),
                linkTo(methodOn(DegreeTypeRestController.class).getAllDegreeTypes()).withRel("all-degree-types")
        );
    }
}
