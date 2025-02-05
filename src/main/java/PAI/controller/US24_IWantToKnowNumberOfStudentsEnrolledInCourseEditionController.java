package PAI.controller;

import PAI.domain.CourseEdition;
import PAI.domain.CourseEditionEnrollmentRepository;

public class US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController {

    CourseEditionEnrollmentRepository _courseEditionEnrollmentRepository;

    public US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(CourseEditionEnrollmentRepository courseEditionEnrollmentRepository) {
        _courseEditionEnrollmentRepository = courseEditionEnrollmentRepository;
    }

    public int IWantToKnowNumberOfStudentsEnrolledInCourseEdition(CourseEdition courseEdition) throws Exception {

        return _courseEditionEnrollmentRepository.numberOfStudentsEnrolledInCourseEdition(courseEdition);

    }
}
