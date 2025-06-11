package PAI.assembler.teacherCareerProgression;

import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryResponseDTO;
import org.springframework.hateoas.EntityModel;

public interface IUpdateTeacherCategoryHateoasAssembler {

    EntityModel<UpdateTeacherCategoryResponseDTO> toModel(UpdateTeacherCategoryResponseDTO dto) throws Exception;
}
