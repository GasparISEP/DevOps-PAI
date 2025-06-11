package PAI.assembler.teacher;

import PAI.dto.teacher.TeacherWithRelevantDataDTO;
import org.springframework.hateoas.EntityModel;

public interface ITeacherWithRelevantDataHateoasAssembler {
    EntityModel<TeacherWithRelevantDataDTO> toModel(TeacherWithRelevantDataDTO teacherWithRelevantDataDTO);
}
