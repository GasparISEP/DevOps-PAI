package PAI.controller;

import PAI.VOs.NIF;
import PAI.VOs.Name;
import PAI.domain.Teacher;
import PAI.domain.TeacherCategory;
import PAI.repository.TeacherCategoryRepositoryImpl;
import PAI.repository.TeacherRepository;

import java.util.Optional;

public class US14_UpdateTeachersCategoryController {

    private TeacherRepository _teacherRepository;
    private TeacherCategoryRepositoryImpl _teacherCategoryRepository;

    public US14_UpdateTeachersCategoryController(TeacherRepository teacherRepository, TeacherCategoryRepositoryImpl teacherCategoryRepository) {

        if (teacherRepository == null) {
            throw new IllegalArgumentException("Teacher Repository cannot be null");
        }

        _teacherRepository = teacherRepository;

        if (teacherCategoryRepository == null) {
            throw new IllegalArgumentException("Teacher Category Repository cannot be null");
        }

        _teacherCategoryRepository = teacherCategoryRepository;
    }

    public boolean updateTeacherCategory(String date, NIF teacherNIF, String teacherCategoryName) {
        if (date == null || date.isBlank()) {
            throw new IllegalArgumentException("Date is invalid");
        }
        else if (teacherNIF == null) {
            throw new IllegalArgumentException("Teacher NIF is invalid");
        }
        else if (teacherCategoryName == null || teacherCategoryName.isBlank()) {
            throw new IllegalArgumentException("Teacher Category is invalid");
        }
        else {
            Optional<Teacher> optionalTeacher = _teacherRepository.getTeacherByNIF(teacherNIF);
            Teacher teacher = optionalTeacher.orElseThrow(() ->
                    new IllegalArgumentException("Teacher with NIF " + teacherNIF + " not found"));
            Name nameVO = new Name(teacherCategoryName);
            Optional<TeacherCategory> optionalTeacherCategory = _teacherCategoryRepository.findByName(nameVO);
            TeacherCategory teacherCategory = optionalTeacherCategory.orElseThrow(() ->
                    new IllegalArgumentException("Teacher Category with name " + teacherCategoryName + " not found"));

            return true;
        }
    }
}
