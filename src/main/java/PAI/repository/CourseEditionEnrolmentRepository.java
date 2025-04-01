package PAI.repository;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.domain.*;
import PAI.factory.ICourseEditionEnrolmentFactory;
import PAI.factory.ICourseEditionEnrolmentListFactory;

import java.util.*;

public class CourseEditionEnrolmentRepository {

    private Set<CourseEditionEnrolment> _courseEditionEnrolments;

    private final ICourseEditionEnrolmentFactory _courseEditionEnrolmentFactory;

    //constructor
    public CourseEditionEnrolmentRepository(ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory, ICourseEditionEnrolmentListFactory courseEditionEnrolmentListFactory) {

        _courseEditionEnrolments = courseEditionEnrolmentListFactory.getCourseEditionEnrolmentList();
        _courseEditionEnrolmentFactory = courseEditionEnrolmentFactory;
    }

    public boolean enrolStudentInACourseEdition(StudentID studentId, CourseEditionID courseEditionId) {
        try {
            CourseEditionEnrolment cee1 = _courseEditionEnrolmentFactory.createCourseEditionEnrolment(studentId, courseEditionId);

            return _courseEditionEnrolments.add(cee1);

        } catch (Exception e) {
            return false;
        }
    }

//    public boolean isStudentEnrolledInCourseEdition(StudentID student, CourseEditionID courseEdition) {
//        for (CourseEditionEnrolment enrollment : _courseEditionEnrolments) {
//            if (enrollment.knowStudent().identity().equals(student) &&
//                    enrollment.knowCourseEdition().equals(courseEdition) &&
//                    enrollment.isEnrollmentActive()) {
//                return true;
//            }
//        }
//        return false;
//    }

    public Optional<CourseEditionEnrolment> findByStudentAndEdition(StudentID student, CourseEditionID courseEdition) {
        if (student == null || courseEdition == null) {
            return Optional.empty();
        }
        for (CourseEditionEnrolment courseEEnrollment : _courseEditionEnrolments)
            if (courseEEnrollment.hasStudent(student) && courseEEnrollment.hasCourseEdition(courseEdition)) {
                return Optional.of(courseEEnrollment);
            }
        return Optional.empty();
    }

    //US24
    public int numberOfStudentsEnrolledInCourseEdition(CourseEditionID courseEditionId) throws Exception {

        int count = 0;
        for (CourseEditionEnrolment enrollment : _courseEditionEnrolments) {
            if (enrollment.knowCourseEdition().equals(courseEditionId)) {
                count++;
            }
        }
        return count;
    }

    // Method to remove (deactivate) an enrollment
    public boolean removeEnrolment(StudentID studentID, CourseEditionID courseEditionID) {
        Optional<CourseEditionEnrolment> enrollment = findByStudentAndEdition(studentID, courseEditionID);
        if (enrollment.isPresent()) {
            CourseEditionEnrolment cee = enrollment.get();
            // Deactivate the enrolment if it's active
            if (cee.isEnrolmentActive()) {  // Only deactivates if the enrollment is currently active
                cee.deactivateEnrolment();
                return true; // Returns true indicating that the enrollment was deactivated
            }
        }
        return false; // Returns false if the enrollment was already inactive or did not exist
    }


    public void enrolStudentInProgrammeCourseEditions(StudentID studentId, List<CourseEditionID> courseEditions){

        for (CourseEditionID courseEditionId : courseEditions) {
            Optional<CourseEditionEnrolment> existingEnrollment = findByStudentAndEdition(studentId, courseEditionId);
            if (existingEnrollment.isPresent()) {
                throw new IllegalStateException("This course edition enrolment is already in the list.");
            }
            enrolStudentInACourseEdition(studentId, courseEditionId);
        }
    }
}


