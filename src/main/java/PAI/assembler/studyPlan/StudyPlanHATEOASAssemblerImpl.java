package PAI.assembler.studyPlan;

import PAI.controllerRest.ProgrammeRestController;
import PAI.dto.studyPlan.StudyPlanResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudyPlanHATEOASAssemblerImpl implements IStudyPlanHATEOASAssembler {

    @Override
    @NonNull
    public EntityModel<StudyPlanResponseDTO> toModel(@NonNull StudyPlanResponseDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(ProgrammeRestController.class)
                        .getStudyPlanByGeneratedID(dto.getUUID()))
                        .withSelfRel(),

                linkTo(methodOn(ProgrammeRestController.class)
                        .getProgrammeByID(dto.getProgrammeAcronym()))
                        .withRel("programme"));
    }
}
