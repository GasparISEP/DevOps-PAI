package PAI.mapper;

import PAI.domain.TeacherCategory;
import PAI.persistence.datamodel.TeacherCategoryDataModel;

public interface ITeacherCategoryMapper {
    TeacherCategoryDataModel toDataModel(TeacherCategory domain);
    TeacherCategory toDomainModel(TeacherCategoryDataModel data);
}