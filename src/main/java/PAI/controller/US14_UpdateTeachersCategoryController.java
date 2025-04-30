package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.domain.TeacherCategory;
import PAI.service.teacherCareerProgression.ITeacherCareerProgressionService;
import PAI.service.ITeacherCategoryService;
import PAI.service.ITeacherService;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class US14_UpdateTeachersCategoryController {

    private ITeacherService _teacherService;
    private ITeacherCategoryService _teacherCategoryService;
    private ITeacherCareerProgressionService _teacherCareerProgressionService;

    public US14_UpdateTeachersCategoryController(ITeacherService teacherService, ITeacherCategoryService teacherCategoryService, ITeacherCareerProgressionService teacherCareerProgressionService) {
        _teacherService = Objects.requireNonNull(teacherService, "Teacher Service cannot be null!");
        _teacherCategoryService = Objects.requireNonNull(teacherCategoryService, "Teacher Category Service cannot be null!");
        _teacherCareerProgressionService = Objects.requireNonNull(teacherCareerProgressionService, "Teacher Career Progression Service cannot be null!");
    }

    public Iterable<Teacher> getAllTeachers() {
        return _teacherService.getAllTeachers();
    }

    public Iterable<TeacherCategory> getAllTeacherCategories() {
        return _teacherCategoryService.getAllTeacherCategories();
    }


    public boolean updateTeacherCategoryInTeacherCareerProgression (String date, UUID teacherCategoryID, String teacherAcronym) {
        try {
            //Create VOs
            Date dateVO = new Date(date);
            TeacherCategoryID teacherCategoryIDVO = new TeacherCategoryID(teacherCategoryID);
            TeacherAcronym acronymVO = new TeacherAcronym(teacherAcronym);
            TeacherID teacherIDVO = new TeacherID(acronymVO);

            //If teacher and teacherCategory exists, update Teacher's Category
            if (!_teacherService.existsById(teacherIDVO) || !_teacherCategoryService.existsById(teacherCategoryIDVO)) {
                return false;
            }

            _teacherCareerProgressionService.updateTeacherCategoryInTeacherCareerProgression(dateVO, teacherCategoryIDVO, teacherIDVO);
        } catch (Exception e){
            return false;
        }

        return true;
    }
}
