package PAI.controller;

import PAI.domain.CourseEdition;
import PAI.repository.CourseEditionEnrollmentRepository;

public class US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController {

    private CourseEditionEnrollmentRepository _courseEditionEnrollmentRepository;

    public US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(CourseEditionEnrollmentRepository courseEditionEnrollmentRepository) {
        _courseEditionEnrollmentRepository = courseEditionEnrollmentRepository;
    }

    public int IWantToKnowNumberOfStudentsEnrolledInCourseEdition(CourseEdition courseEdition) throws Exception {

        return _courseEditionEnrollmentRepository.numberOfStudentsEnrolledInCourseEdition(courseEdition);

    }
}
