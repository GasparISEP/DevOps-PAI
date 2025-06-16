package PAI.assembler.teacherCareerProgression;

import PAI.controllerRest.TeacherCareerProgressionRestController;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UpdateTeacherCategoryHateoasAssemblerImpl implements IUpdateTeacherCategoryHateoasAssembler {

    @Override
    public EntityModel<UpdateTeacherCategoryResponseDTO> toModel(UpdateTeacherCategoryResponseDTO dto) throws Exception {
        try {
            return EntityModel.of(dto,
                    linkTo(methodOn(TeacherCareerProgressionRestController.class)
                            .getTeacherCareerProgressionByID(dto.teacherCareerProgressionId()))
                            .withSelfRel(),
                    linkTo(methodOn(TeacherCareerProgressionRestController.class)
                            .getAllTeacherCareerProgression())
                            .withRel("all"),

                    // UI links for frontend (React)
                    Link.of("/teacher-career-progressions/" + dto.teacherCareerProgressionId()).withRel("details"),
                    Link.of("/teacher-career-progressions").withRel("collection")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
