package PAI.controller;

import PAI.VOs.CourseEditionGeneratedID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.teacher.Teacher;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.courseEdition.IDefineRucService;
import PAI.service.teacher.ITeacherService;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Domain controller responsible for orchestrating the assignment of a RUC (coordinator)
 * to a course edition, using services for CourseEdition and Teacher.
 */
@Component
public class US20_DefineRucForCourseEditionController {

    private final IDefineRucService defineRucService;

    /**
     * @param defineRucService
     */
    public US20_DefineRucForCourseEditionController(IDefineRucService defineRucService) {
        this.defineRucService  = Objects.requireNonNull(defineRucService,  "defineRucService cannot be null");
    }

    /**
     * Returns all available teachers.
     *
     * @return list of Teacher
     */
    public Iterable<Teacher> getAllTeachers() {
        return defineRucService.findAllTeachers();
    }

    /**
     * Returns all existing course editions.
     *
     * @return list of CourseEdition
     */
    public Iterable<CourseEdition> getAllCourseEditions() {
        return defineRucService.findAll();
    }

    /**
     * Assigns a RUC (coordinator) to the given course edition.
     *
     * @param courseEditionID courseEditionID  ID of the course edition
     * @param teacherID teacherID              ID of the teacher to be assigned as RUC
     * @return true if the assignment is successful; false if the record does not exist
     * @throws IllegalArgumentException IllegalArgumentException if the IDs are null or not found
     */

    public boolean defineRucForCourseEdition(CourseEditionGeneratedID courseEditionID, TeacherID teacherID) throws Exception {

        Objects.requireNonNull(courseEditionID,"courseEditionID cannot be null");
        Objects.requireNonNull(teacherID,"teacherID cannot be null");

        return defineRucService.assignRucToCourseEdition(teacherID, courseEditionID);
    }
}
