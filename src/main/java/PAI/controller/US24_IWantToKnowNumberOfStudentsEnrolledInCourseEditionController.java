package PAI.controller;

import PAI.domain.CourseEdition;
import PAI.repository.CourseEditionEnrolmentRepository;

public class US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController {

    private CourseEditionEnrolmentRepository _courseEditionEnrolmentRepository;

    public US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(CourseEditionEnrolmentRepository courseEditionEnrolmentRepository) {

        if (courseEditionEnrolmentRepository == null) {
            throw new IllegalArgumentException("Course Edition Enrolment Repository cannot be null");
        }

        _courseEditionEnrolmentRepository = courseEditionEnrolmentRepository;
    }

//    public int IWantToKnowNumberOfStudentsEnrolledInCourseEdition(CourseEdition courseEdition) throws Exception {
//
//        return _courseEditionEnrolmentRepository.numberOfStudentsEnrolledInCourseEdition(courseEdition);
//
//    }
}
