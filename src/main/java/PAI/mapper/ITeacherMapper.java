package PAI.mapper;

import PAI.domain.Teacher;
import PAI.persistence.datamodel.TeacherDataModel;

public interface ITeacherMapper {

    TeacherDataModel toDataModel(Teacher teacher);

    Teacher toDomain(TeacherDataModel teacherDataModel) throws Exception;
}