package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;
import org.springframework.stereotype.Component;


@Component
public class US28_RemoveTheEnrolmentOfAStudentInACourseEditionController {

    private final ICourseEditionEnrolmentService courseEditionEnrolmentService;

    public US28_RemoveTheEnrolmentOfAStudentInACourseEditionController(ICourseEditionEnrolmentService courseEditionEnrolmentService) {

        this.courseEditionEnrolmentService = courseEditionEnrolmentService;
    }
    public boolean removeCourseEditionEnrolment(StudentID studentId, CourseEditionID courseEditionId) throws Exception {
        if (studentId == null || courseEditionId == null) {
            return false; // Returns false immediately if any ID is null
        }

        return courseEditionEnrolmentService.removeCourseEditionEnrolment(studentId, courseEditionId);
    }
}
