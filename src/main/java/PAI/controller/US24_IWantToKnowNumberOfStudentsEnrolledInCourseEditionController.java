package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.persistence.mem.CourseEditionEnrolmentRepositoryImpl;

public class US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController {

    private CourseEditionEnrolmentRepositoryImpl _courseEditionEnrolmentRepositoryImpl;

    public US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepositoryImpl) {

        if (courseEditionEnrolmentRepositoryImpl == null) {
            throw new IllegalArgumentException("Course Edition Enrolment Repository cannot be null");
        }

        _courseEditionEnrolmentRepositoryImpl = courseEditionEnrolmentRepositoryImpl;
    }

   public int IWantToKnowNumberOfStudentsEnrolledInCourseEdition(CourseEditionID courseEditionID) throws Exception {

        return _courseEditionEnrolmentRepositoryImpl.numberOfStudentsEnrolledInCourseEdition(courseEditionID);

    }
}
