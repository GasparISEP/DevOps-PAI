package PAI.assembler.teacher;

import PAI.domain.teacher.Teacher;
import PAI.dto.teacher.RegisterTeacherCommandDTO;
import PAI.dto.teacher.RegisterTeacherRequestDTO;
import PAI.dto.teacher.TeacherDTO;

public interface ITeacherAssembler {

    RegisterTeacherCommandDTO toRegisterTeacherCommandDTO(RegisterTeacherRequestDTO registerTeacherRequestDTO);

    TeacherDTO toDTO(Teacher teacher);

    Iterable<TeacherDTO> toDTOs(Iterable<Teacher> teachers);
}
