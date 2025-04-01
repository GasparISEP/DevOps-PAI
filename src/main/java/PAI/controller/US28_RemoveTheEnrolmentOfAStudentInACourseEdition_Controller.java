package PAI.controller;

import PAI.domain.CourseEdition_2;
import PAI.repository.CourseEditionEnrolmentRepository;
import PAI.domain.CourseEdition;
import PAI.domain.Student;

public class US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller {

    private final CourseEditionEnrolmentRepository courseEditionEnrolmentRepository;

    public US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(CourseEditionEnrolmentRepository courseEditionEnrolmentRepository) {

        this.courseEditionEnrolmentRepository = courseEditionEnrolmentRepository;
    }
    public boolean removeStudentEnrolment(Student student, CourseEdition_2 courseEdition) {

           return courseEditionEnrolmentRepository.removeEnrolment(student, courseEdition);
    }
}
