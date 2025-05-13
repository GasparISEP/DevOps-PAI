package PAI.mapper.teacherCategory;

import PAI.domain.teacherCategory.TeacherCategory;
import PAI.persistence.datamodel.teacherCategory.TeacherCategoryDataModel;

public interface ITeacherCategoryMapper {
    TeacherCategoryDataModel toDataModel(TeacherCategory domain);
    TeacherCategory toDomainModel(TeacherCategoryDataModel data);
}