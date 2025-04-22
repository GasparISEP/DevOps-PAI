package PAI.mapper;

import PAI.VOs.TeacherAcademicEmail;
import PAI.persistence.datamodel.TeacherAcademicEmailDataModel;

public interface ITeacherAcademicEmailMapper {

    TeacherAcademicEmailDataModel toDataModel(TeacherAcademicEmail teacherAcademicEmail);

    TeacherAcademicEmail toDomain(TeacherAcademicEmailDataModel teacherAcademicEmailDataModel);
}