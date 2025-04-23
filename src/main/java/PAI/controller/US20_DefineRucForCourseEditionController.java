package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.CourseEdition;
import PAI.domain.Teacher;
import PAI.service.courseEdition.ICourseEditionService;

public class US20_DefineRucForCourseEditionController {

    private final ICourseEditionService service;

    public US20_DefineRucForCourseEditionController(ICourseEditionService service) {
        this.service = service;
    }

//    public Iterable<Teacher> getAllTeachers() {
//        return service.getAllTeachers();
//    }
//
//    public Iterable<CourseEdition> getAllCourseEditions() {
//        return service.getAllCourseEditions();
//    }
//
//    public boolean defineRucForCourseEdition(CourseEditionID courseEditionID, TeacherID teacherID) {
//        return service.assignRucToCourseEdition(teacherID, courseEditionID);
//    }
}
