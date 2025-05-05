package PAI.mapper.teacher;

import PAI.domain.Teacher;
import PAI.persistence.datamodel.teacher.TeacherDataModel;

public interface ITeacherMapper {

    TeacherDataModel toDataModel(Teacher teacher);

    Teacher toDomain(TeacherDataModel teacherDataModel);
}