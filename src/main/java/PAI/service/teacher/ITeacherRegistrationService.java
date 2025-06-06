package PAI.service.teacher;

import PAI.VOs.TeacherID;
import PAI.domain.teacher.Teacher;
import PAI.dto.teacher.RegisterTeacherCommandDTO;

import java.util.Optional;

public interface ITeacherRegistrationService {

    Teacher createAndSaveTeacher(RegisterTeacherCommandDTO registerTeacherCommandDTO) throws Exception;

    Iterable<Teacher> getAllTeachers();

    public Optional<Teacher> getTeacherById(TeacherID teacherID);
}
