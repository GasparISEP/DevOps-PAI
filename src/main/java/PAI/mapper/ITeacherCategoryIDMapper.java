package PAI.mapper;

import PAI.VOs.TeacherCategoryID;
import PAI.persistence.datamodel.TeacherCategoryIDDataModel;

public interface ITeacherCategoryIDMapper {
    TeacherCategoryIDDataModel toDataModel(TeacherCategoryID domainId);
    TeacherCategoryID toDomainModel(TeacherCategoryIDDataModel dataModelId);
}