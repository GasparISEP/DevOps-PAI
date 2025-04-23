package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.CourseEdition;
import PAI.domain.Teacher;
import PAI.service.ICourseEditionApplicationService;

public class US20_DefineRucForCourseEditionController {

    private final ICourseEditionApplicationService service;

    public US20_DefineRucForCourseEditionController(ICourseEditionApplicationService service) {
        this.service = service;
    }

    public Iterable<Teacher> getAllTeachers() {
        return service.getAllTeachers();
    }

    public Iterable<CourseEdition> getAllCourseEditions() {
        return service.getAllCourseEditions();
    }

    public boolean defineRucForCourseEdition(CourseEditionID courseEditionID, TeacherID teacherID) throws Exception {
        return service.assignRucToCourseEdition(teacherID, courseEditionID);
    }
}
