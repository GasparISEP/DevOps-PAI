package PAI.assembler.teacher;

import PAI.VOs.DepartmentID;
import PAI.VOs.TeacherID;
import PAI.domain.teacher.Teacher;
import PAI.dto.teacher.RegisterTeacherCommandDTO;
import PAI.dto.teacher.RegisterTeacherRequestDTO;
import PAI.dto.teacher.TeacherDTO;
import PAI.dto.teacher.TeacherIdDTO;

public interface ITeacherAssembler {

    RegisterTeacherCommandDTO toRegisterTeacherCommandDTO(RegisterTeacherRequestDTO registerTeacherRequestDTO);

    TeacherDTO toDTO(Teacher teacher);

    TeacherIdDTO toIdDTO(Teacher teacher);

    Iterable<TeacherDTO> toDTOs(Iterable<Teacher> teachers);

    Iterable<TeacherIdDTO> toIdDTOs (Iterable<Teacher> teachers);

    TeacherID fromStringToTeacherID(String id);
}
