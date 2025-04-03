package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.repository.ICourseEditionEnrolmentRepository;

public class US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller {

    private final ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository;

    public US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository) {

        this.courseEditionEnrolmentRepository = courseEditionEnrolmentRepository;
    }
    public boolean removeStudentEnrolment(StudentID studentId, CourseEditionID courseEditionId) {
        if (studentId == null || courseEditionId == null) {
            return false; // Returns false immediately if any ID is null
        }

        return courseEditionEnrolmentRepository.removeEnrolment(studentId, courseEditionId);
    }
}
