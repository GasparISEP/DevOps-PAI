package PAI.controller;

import PAI.repository.CourseEditionEnrollmentRepository;
import PAI.domain.CourseEdition;
import PAI.domain.Student;

import java.util.List;

public class US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller {

    private final CourseEditionEnrollmentRepository courseEditionEnrollmentRepository;

    public US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(CourseEditionEnrollmentRepository courseEditionEnrollmentRepository) {

        this.courseEditionEnrollmentRepository = courseEditionEnrollmentRepository;
    }
    public boolean removeStudentEnrollment(Student student,  CourseEdition courseEditions) {

           return courseEditionEnrollmentRepository.removeEnrollment(student, courseEditions);
    }
}
