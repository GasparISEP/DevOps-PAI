package PAI.service.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.teacherCategory.TeacherCategory;

public interface ITeacherCategoryService {

    TeacherCategory configureTeacherCategory(Name teacherCategoryName) throws Exception;

    boolean existsById(TeacherCategoryID teacherCategoryID);

    Iterable<TeacherCategory> getAllTeacherCategories();
}