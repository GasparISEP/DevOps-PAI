package PAI.controller;

import PAI.domain.CourseEdition;
import PAI.domain.CourseEditionEnrollmentRepository;

public class US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition {

    CourseEditionEnrollmentRepository _courseEditionEnrollmentRepository;

    public US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition(CourseEditionEnrollmentRepository courseEditionEnrollmentRepository) {
        _courseEditionEnrollmentRepository = courseEditionEnrollmentRepository;
    }

    public int IWantToKnowNumberOfStudentsEnrolledInCourseEdition(CourseEdition courseEdition) {

        return _courseEditionEnrollmentRepository.numberOfStudentsEnrolledInCourseEdition(courseEdition);

    }
}
