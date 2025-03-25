package PAI.controller;

import PAI.domain.*;
import PAI.repository.TeacherCategoryRepository;
import PAI.repository.TeacherRepository;

import java.util.Optional;

public class US14_UpdateTeachersCategoryController {

    private TeacherRepository _teacherRepository;
    private TeacherCategoryRepository _teacherCategoryRepository;

    public US14_UpdateTeachersCategoryController(TeacherRepository teacherRepository, TeacherCategoryRepository teacherCategoryRepository) {

        if (teacherRepository == null) {
            throw new IllegalArgumentException("Teacher Repository cannot be null");
        }

        _teacherRepository = teacherRepository;

        if (teacherCategoryRepository == null) {
            throw new IllegalArgumentException("Teacher Category Repository cannot be null");
        }

        _teacherCategoryRepository = teacherCategoryRepository;
    }

    public boolean updateTeacherCategory(String date, String teacherNIF, String teacherCategoryName) {
        if (date == null || date.isBlank()) {
            throw new IllegalArgumentException("Date is invalid");
        }
        else if (teacherNIF == null || teacherNIF.isBlank()) {
            throw new IllegalArgumentException("Teacher NIF is invalid");
        }
        else if (teacherCategoryName == null || teacherCategoryName.isBlank()) {
            throw new IllegalArgumentException("Teacher Category is invalid");
        }
        else {
            Optional<Teacher> optionalTeacher = _teacherRepository.getTeacherByNIF(teacherNIF);
            Teacher teacher = optionalTeacher.orElseThrow(() ->
                    new IllegalArgumentException("Teacher with NIF " + teacherNIF + " not found"));
            Optional<TeacherCategory> optionalTeacherCategory = _teacherCategoryRepository.getTeacherCategoryByName(teacherCategoryName);
            TeacherCategory teacherCategory = optionalTeacherCategory.orElseThrow(() ->
                    new IllegalArgumentException("Teacher Category with name " + teacherCategoryName + " not found"));

            teacher.updateTeacherCategoryInTeacherCareer(date, teacherCategory);
            return true;
        }
    }
}
