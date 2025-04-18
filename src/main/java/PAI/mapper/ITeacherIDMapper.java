package PAI.mapper;

import PAI.VOs.TeacherID;
import PAI.persistence.datamodel.TeacherIDDataModel;

public interface ITeacherIDMapper {

    TeacherID toDomain (TeacherIDDataModel teacherIDDataModel);

    TeacherIDDataModel toDataModel (TeacherID teacherID);
}
