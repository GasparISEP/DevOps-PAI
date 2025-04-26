package PAI.service;

import PAI.VOs.TeacherCategoryID;
import PAI.domain.TeacherCategory;

public interface ITeacherCategoryService {
    boolean registerCategory(String categoryName) throws Exception;

    boolean existsById(TeacherCategoryID teacherCategoryID);

    Iterable<TeacherCategory> getAllTeacherCategories();
}