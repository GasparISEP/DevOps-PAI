package PAI.assembler.teacher;

import PAI.domain.teacher.Teacher;
import PAI.dto.teacher.TeacherDTO;

public interface ITeacherAssembler {

    TeacherDTO toDTO(Teacher teacher);
}
