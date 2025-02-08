package PAI.controller;

import PAI.domain.CourseEdition;
import PAI.domain.CourseEditionRepository;
import PAI.domain.Teacher;
import PAI.domain.TeacherRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class US20_DefineRucForCourseEditionController {

    private CourseEditionRepository courseEditionRepository;
    private TeacherRepository teacherRepository;

    // Constructor
    public US20_DefineRucForCourseEditionController(CourseEditionRepository courseEditionRepository, TeacherRepository teacherRepository) {
        this.courseEditionRepository = courseEditionRepository;
        this.teacherRepository = teacherRepository;
    }

    // Method to get all Course Editions
    public List<CourseEdition> getCourseEditions() {
        return courseEditionRepository.getCourseEditions();
    }

    // Method to get all Teachers
    public List<Teacher> getTeachers() {
        return teacherRepository.getAllTeachers();
    }

    // Define RUC for a specific Course Edition
    public boolean defineRucForCourseEdition(CourseEdition courseEdition, Teacher teacher) {

        return courseEditionRepository.setRucInACourseEdition(courseEdition, teacher);
    }
}