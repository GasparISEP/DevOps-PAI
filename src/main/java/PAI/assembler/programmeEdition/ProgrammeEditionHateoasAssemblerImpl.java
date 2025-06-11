package PAI.assembler.programmeEdition;

import PAI.assembler.accessMethod.IAccessMethodHateoasAssembler;
import PAI.controllerRest.AccessMethodRestController;
import PAI.controllerRest.ProgrammeEditionRestController;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static PAI.utils.ValidationUtils.validateNotNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProgrammeEditionHateoasAssemblerImpl implements
        RepresentationModelAssembler<ProgrammeEditionResponseDTO, EntityModel<ProgrammeEditionResponseDTO>>,
        IProgrammeEditionHateoasAssembler {

    @Override
    public EntityModel<ProgrammeEditionResponseDTO> toModel(ProgrammeEditionResponseDTO dto) {
        validateNotNull(dto, "ProgrammeEditionResponseDTO");

        return EntityModel.of(dto,
                linkTo(methodOn(ProgrammeEditionRestController.class)
                        .getProgrammeEditionsByProgrammeID(dto.programme().acronym())).withSelfRel(),

                linkTo(methodOn(ProgrammeEditionRestController.class)
                        .getAllProgrammeEditions()).withRel("collection"),

                linkTo(methodOn(ProgrammeEditionRestController.class)
                        .getNumberOfStudents(dto.programme().acronym(), dto.schoolYearId()))
                        .withRel("numberOfStudents")
        );
    }
}
