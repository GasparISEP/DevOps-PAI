package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.repository.CourseEditionEnrolmentRepository;
import PAI.domain.CourseEdition;
import PAI.domain.Student;

public class US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller {

    private final CourseEditionEnrolmentRepository courseEditionEnrolmentRepository;

    public US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(CourseEditionEnrolmentRepository courseEditionEnrolmentRepository) {

        this.courseEditionEnrolmentRepository = courseEditionEnrolmentRepository;
    }
//    public boolean removeStudentEnrolment(StudentID studentId, CourseEditionID courseEditionsId) {
//
//           return courseEditionEnrolmentRepository.removeEnrolment(studentId, courseEditionsId);
//    }
}
