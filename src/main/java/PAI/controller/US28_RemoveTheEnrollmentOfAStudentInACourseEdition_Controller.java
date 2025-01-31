package PAI.controller;

import PAI.domain.CourseEditionEnrollmentRepository;
import PAI.domain.CourseEdition;
import PAI.domain.Student;

public class US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller {
        private CourseEditionEnrollmentRepository courseEditionEnrollmentRepository;

        public US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(CourseEditionEnrollmentRepository courseEditionEnrollmentRepository) {
            this.courseEditionEnrollmentRepository = courseEditionEnrollmentRepository;
        }
        public boolean removeStudentEnrolment(Student student, CourseEdition courseEdition) {
            return courseEditionEnrollmentRepository.removeEnrollment(student, courseEdition);
        }
    }
