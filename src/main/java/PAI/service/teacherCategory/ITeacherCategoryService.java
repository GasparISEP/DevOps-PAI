package PAI.service.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryDTO;

public interface ITeacherCategoryService {

    TeacherCategoryDTO configureTeacherCategory(Name teacherCategoryName) throws Exception;

    boolean existsById(TeacherCategoryID teacherCategoryID);

    Iterable<TeacherCategory> getAllTeacherCategories();
}