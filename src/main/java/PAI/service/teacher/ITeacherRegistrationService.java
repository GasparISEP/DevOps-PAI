package PAI.service.teacher;

import PAI.domain.teacher.Teacher;
import PAI.dto.teacher.RegisterTeacherCommandDTO;

public interface ITeacherRegistrationService {

    Teacher createAndSaveTeacher(RegisterTeacherCommandDTO registerTeacherCommandDTO) throws Exception;
}
