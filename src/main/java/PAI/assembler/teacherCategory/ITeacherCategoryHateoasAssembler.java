package PAI.assembler.teacherCategory;

import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;
import org.springframework.hateoas.EntityModel;

public interface ITeacherCategoryHateoasAssembler {

    EntityModel<TeacherCategoryResponseDTO> toModel(TeacherCategoryResponseDTO dto) throws Exception;
}
