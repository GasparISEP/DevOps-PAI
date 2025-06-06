package PAI.assembler.teacherCareerProgression;

import PAI.dto.teacherCareerProgression.UpdateTeacherWorkingPercentageResponseDTO;
import org.springframework.hateoas.EntityModel;

public interface IUpdateTeacherWorkingPercentageHateoasAssembler {

    EntityModel<UpdateTeacherWorkingPercentageResponseDTO> toModel(UpdateTeacherWorkingPercentageResponseDTO dto);
}
