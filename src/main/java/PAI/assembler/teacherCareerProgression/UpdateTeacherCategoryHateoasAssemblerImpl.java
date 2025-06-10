package PAI.assembler.teacherCareerProgression;

import PAI.controllerRest.TeacherCareerProgressionRestController;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

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
                            .withRel("all")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
