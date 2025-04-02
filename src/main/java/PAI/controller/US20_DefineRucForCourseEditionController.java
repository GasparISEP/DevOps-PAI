package PAI.controller;

import PAI.VOs.TeacherID;
import PAI.domain.CourseEdition_2;
import PAI.domain.Teacher;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.ITeacherRepository;

public class US20_DefineRucForCourseEditionController {

    private final ICourseEditionRepository _iCourseEditionRepository;
    private final ITeacherRepository _iTeacherRepository;

    public US20_DefineRucForCourseEditionController(ICourseEditionRepository iCourseEditionRepository, ITeacherRepository iTeacherRepository) {
        this._iCourseEditionRepository = iCourseEditionRepository;
        this._iTeacherRepository = iTeacherRepository;
    }

<<<<<<< Updated upstream
    // Method to get all Course Editions
    public List<CourseEdition_2> getCourseEditions() {
        return _courseEditionRepository.getCourseEditions();
    }

    // Method to get all Teachers
    public Iterable<Teacher> getTeachers() {
        return _teacherRepository.findAll();
    }

    // Define RUC for a specific Course Edition
    public boolean defineRucForCourseEdition(CourseEdition courseEdition, Teacher teacher) {
        if (teacher == null) {
=======
    public boolean defineRucForCourseEdition(CourseEdition_2 courseEdition_2, TeacherID teacherID) {
        if (courseEdition_2 == null || teacherID == null) {
>>>>>>> Stashed changes
            return false;
        }

        return courseEdition_2.setRuc(teacherID);
    }

    public Iterable<Teacher> getAllTeachers() {
        return _iTeacherRepository.findAll();
    }

    public Iterable<CourseEdition_2> getAllCourseEditions() {
        return _iCourseEditionRepository.findAll();
    }
}