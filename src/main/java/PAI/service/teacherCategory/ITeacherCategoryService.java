package PAI.service.teacherCategory;

import PAI.VOs.TeacherCategoryID;
import PAI.domain.teacherCategory.TeacherCategory;

public interface ITeacherCategoryService {
    boolean registerCategory(String categoryName) throws Exception;

    boolean existsById(TeacherCategoryID teacherCategoryID);

    Iterable<TeacherCategory> getAllTeacherCategories();
}