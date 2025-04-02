package PAI.controller;

import PAI.VOs.TeacherID;
import PAI.domain.CourseEditionDDD;
import PAI.domain.Teacher;
import PAI.repository.ICourseEditionRepositoryDDD;
import PAI.repository.ITeacherRepository;

public class US20_DefineRucForCourseEditionController {

    private final ICourseEditionRepositoryDDD _iCourseEditionRepository;
    private final ITeacherRepository _iTeacherRepository;

    public US20_DefineRucForCourseEditionController(ICourseEditionRepositoryDDD iCourseEditionRepository, ITeacherRepository iTeacherRepository) {
        this._iCourseEditionRepository = iCourseEditionRepository;
        this._iTeacherRepository = iTeacherRepository;
    }

    public Iterable<Teacher> getAllTeachers() {
        return _iTeacherRepository.findAll();
    }

    public Iterable<CourseEditionDDD> getAllCourseEditions() {
        return _iCourseEditionRepository.findAll();
    }

    public boolean defineRucForCourseEdition(CourseEditionDDD courseEdition_2, TeacherID teacherID) {
        if (courseEdition_2 == null || teacherID == null) {
            return false;
        }

        return courseEdition_2.setRuc(teacherID);
    }
}