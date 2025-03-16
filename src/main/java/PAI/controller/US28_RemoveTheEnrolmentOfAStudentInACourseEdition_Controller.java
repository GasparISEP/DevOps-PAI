package PAI.controller;

import PAI.repository.CourseEditionEnrolmentRepository;
import PAI.domain.CourseEdition;
import PAI.domain.Student;

public class US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller {

    private final CourseEditionEnrolmentRepository courseEditionEnrolmentRepository;

    public US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(CourseEditionEnrolmentRepository courseEditionEnrolmentRepository) {

        this.courseEditionEnrolmentRepository = courseEditionEnrolmentRepository;
    }
    public boolean removeStudentEnrollment(Student student,  CourseEdition courseEditions) {

           return courseEditionEnrolmentRepository.removeEnrollment(student, courseEditions);
    }
}
