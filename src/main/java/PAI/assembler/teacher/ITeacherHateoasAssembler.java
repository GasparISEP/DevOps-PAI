package PAI.assembler.teacher;

import PAI.dto.teacher.TeacherDTO;
import org.springframework.hateoas.EntityModel;

public interface ITeacherHateoasAssembler {

    EntityModel<TeacherDTO> toModel (TeacherDTO teacherDTO);
}
