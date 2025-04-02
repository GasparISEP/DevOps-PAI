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

    public boolean defineRucForCourseEdition(CourseEdition_2 courseEdition_2, TeacherID teacherID) {
        if (courseEdition_2 == null || teacherID == null) {
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