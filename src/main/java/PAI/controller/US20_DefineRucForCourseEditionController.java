package PAI.controller;

import PAI.domain.CourseEdition;
import PAI.repository.CourseEditionRepository;
import PAI.domain.Teacher;
import PAI.domain.TeacherRepository;
import java.util.List;

public class US20_DefineRucForCourseEditionController {

    private CourseEditionRepository _courseEditionRepository;
    private TeacherRepository _teacherRepository;

    // Constructor
    public US20_DefineRucForCourseEditionController(CourseEditionRepository courseEditionRepository, TeacherRepository teacherRepository) {
        this._courseEditionRepository = courseEditionRepository;
        this._teacherRepository = teacherRepository;
    }

    // Method to get all Course Editions
    public List<CourseEdition> getCourseEditions() {
        return _courseEditionRepository.getCourseEditions();
    }

    // Method to get all Teachers
    public List<Teacher> getTeachers() {
        return _teacherRepository.getAllTeachers();
    }

    // Define RUC for a specific Course Edition
    public boolean defineRucForCourseEdition(CourseEdition courseEdition, Teacher teacher) {
        if (teacher == null) {
            return false;
        }
        return _courseEditionRepository.setRucInACourseEdition(courseEdition, teacher);
    }
}