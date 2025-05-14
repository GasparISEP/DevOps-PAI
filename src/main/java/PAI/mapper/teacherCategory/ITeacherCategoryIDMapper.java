package PAI.mapper.teacherCategory;

import PAI.VOs.TeacherCategoryID;
import PAI.persistence.datamodel.teacherCategory.TeacherCategoryIDDataModel;

public interface ITeacherCategoryIDMapper {
    TeacherCategoryIDDataModel toDataModel(TeacherCategoryID domainId);
    TeacherCategoryID toDomainModel(TeacherCategoryIDDataModel dataModelId);
}