package PAI.assembler.programme;

import PAI.controllerRest.ProgrammeRestController;
import PAI.dto.Programme.ProgrammeDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ProgrammeHATEOASAssembler implements RepresentationModelAssembler<ProgrammeDTO, EntityModel<ProgrammeDTO>>, IProgrammeHATEOASAssembler {

    @Override
    public EntityModel<ProgrammeDTO> toModel(ProgrammeDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(ProgrammeRestController.class)
                        .getProgrammeByID(dto.acronym()))
                        .withSelfRel(),

                linkTo(methodOn(ProgrammeRestController.class)
                        .getAllProgrammes())
                        .withRel("all")
        );
    }
}
