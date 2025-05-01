package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.service.ICourseEditionEnrolmentService;

public class US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController {

    private ICourseEditionEnrolmentService _courseEditionEnrolmentService;

    public US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(ICourseEditionEnrolmentService  courseEditionEnrolmentService) {

        if (courseEditionEnrolmentService == null) {
            throw new IllegalArgumentException("Course Edition Enrolment Repository cannot be null");
        }

        _courseEditionEnrolmentService = courseEditionEnrolmentService;
    }

   public int IWantToKnowNumberOfStudentsEnrolledInCourseEdition(CourseEditionID courseEditionID) throws Exception {

        return _courseEditionEnrolmentService.numberOfStudentsEnrolledInCourseEdition(courseEditionID);

    }
}
