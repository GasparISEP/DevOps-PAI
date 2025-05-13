package PAI.mapper.teacherCategoryID;

import PAI.VOs.TeacherCategoryID;
import PAI.persistence.datamodel.teacherCategoryID.TeacherCategoryIDDataModel;

public interface ITeacherCategoryIDMapper {
    TeacherCategoryIDDataModel toDataModel(TeacherCategoryID domainId);
    TeacherCategoryID toDomainModel(TeacherCategoryIDDataModel dataModelId);
}