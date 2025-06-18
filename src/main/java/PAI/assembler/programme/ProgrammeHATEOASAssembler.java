package PAI.assembler.programme;

import PAI.controllerRest.ProgrammeRestController;
import PAI.dto.Programme.ProgrammeIDDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProgrammeHATEOASAssembler implements RepresentationModelAssembler<ProgrammeIDDTO, EntityModel<ProgrammeIDDTO>>, IProgrammeHATEOASAssembler {

    @Override
    public EntityModel<ProgrammeIDDTO> toModel(ProgrammeIDDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(ProgrammeRestController.class)
                        .getProgrammeByID(dto.acronym()))
                        .withSelfRel(),

                linkTo(methodOn(ProgrammeRestController.class)
                        .getAllProgrammes())
                        .withRel("all"),

                linkTo(methodOn(ProgrammeRestController.class)
                        .registerStudyPlan(dto.acronym(), null)) // null placeholder for @Requesparam
                        .withRel("registerStudyPlan"),

                linkTo(methodOn(ProgrammeRestController.class)
                        .getStudyPlansForProgramme(dto.acronym()))
                        .withRel("study-plans"));
    }
}
