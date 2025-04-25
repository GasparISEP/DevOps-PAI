package PAI.service;

import PAI.VOs.TeacherCategoryID;

public interface ITeacherCategoryService {
    boolean registerCategory(String categoryName) throws Exception;

    boolean existsById(TeacherCategoryID teacherCategoryID);
}