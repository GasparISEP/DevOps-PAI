package PAI.mapper.teacher;

import PAI.VOs.TeacherID;
import PAI.persistence.datamodel.teacher.TeacherIDDataModel;

public interface ITeacherIDMapper {

    TeacherID toDomain (TeacherIDDataModel teacherIDDataModel);

    TeacherIDDataModel toDataModel (TeacherID teacherID);
}
