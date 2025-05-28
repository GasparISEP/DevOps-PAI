package PAI.assembler.teacherCategory;

import PAI.controllerRest.TeacherCategoryRestController;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TeacherCategoryHateoasAssemblerImpl implements
        RepresentationModelAssembler<TeacherCategoryResponseDTO, EntityModel<TeacherCategoryResponseDTO>>,
        ITeacherCategoryHateoasAssembler {

    @Override
    public EntityModel<TeacherCategoryResponseDTO> toModel(TeacherCategoryResponseDTO dto) {
        try {
            return EntityModel.of(dto,
                    linkTo(methodOn(TeacherCategoryRestController.class)
                            .getTeacherCategoryById(dto.id()))
                            .withSelfRel()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
