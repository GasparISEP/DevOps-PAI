package PAI.repository;

import PAI.domain.*;
import PAI.factory.CourseEditionEnrollmentFactory;
import PAI.factory.CourseEditionEnrollmentListFactory;

import java.util.*;

public class CourseEditionEnrollmentRepository {

    private Set<CourseEditionEnrollment> _courseEditionEnrollments;

    private final CourseEditionEnrollmentFactory _courseEditionEnrollmentFactory;

    //constructor
    public CourseEditionEnrollmentRepository(CourseEditionEnrollmentFactory courseEditionEnrollmentFactory, CourseEditionEnrollmentListFactory courseEditionEnrollmentListFactory) {

        _courseEditionEnrollments = courseEditionEnrollmentListFactory.getCourseEditionEnrollmentList();
        _courseEditionEnrollmentFactory = courseEditionEnrollmentFactory;
    }

    public boolean enrollStudentInACourseEdition(Student student, CourseEdition courseEdition) {
        try {
            CourseEditionEnrollment cee1 = _courseEditionEnrollmentFactory.createCourseEditionEnrollment(student, courseEdition);

            return _courseEditionEnrollments.add(cee1);

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStudentEnrolledInCourseEdition(Student student, CourseEdition courseEdition) {
        for (CourseEditionEnrollment enrollment : _courseEditionEnrollments) {
            if (enrollment.knowStudent().equals(student) && enrollment.knowCourseEdition().equals(courseEdition) &&
                    enrollment.isEnrollmentActive()) {
                return true;
            }
        }
        return false;
    }

    public Optional<CourseEditionEnrollment> findByStudentAndEdition(Student student, CourseEdition courseEdition) {
        if (student == null || courseEdition == null) {
            throw new IllegalArgumentException("Student and CourseEdition cannot be null");
        }
        for (CourseEditionEnrollment courseEEnrollments : _courseEditionEnrollments)
            if (courseEEnrollments.findStudentInCourseEditionEnrollment().equals(student) && courseEEnrollments.findCourseEditionInEnrollment().equals(courseEdition)) {
                return Optional.of(courseEEnrollments);
            }
        return Optional.empty();
    }

    //US24
    public int numberOfStudentsEnrolledInCourseEdition(CourseEdition courseEdition) throws Exception {
        validateCourseEdition(courseEdition);

        int count = 0;
        for (CourseEditionEnrollment enrollment : _courseEditionEnrollments) {
            if (enrollment.knowCourseEdition().equals(courseEdition)) {
                count++;
            }
        }
        return count;
    }

    // Private method to validate Course Edition
    private void validateCourseEdition(CourseEdition courseEdition) throws Exception {
        if (courseEdition == null) {
            throw new Exception("Course edition cannot be null.");
        }
    }

    // Method to remove (deactivate) an enrollment
    public boolean removeEnrollment(Student student, CourseEdition courseEdition) {
        Optional<CourseEditionEnrollment> enrollment = findByStudentAndEdition(student, courseEdition);
        if (enrollment.isPresent()) {
            CourseEditionEnrollment cee = enrollment.get();
            if (cee.isEnrollmentActive()) {  // Only deactivates if the enrollment is currently active
                cee.deactivateEnrollment();
                return true; // Returns true indicating that the enrollment was deactivated
            }
        }
        return false; // Returns false if the enrollment was already inactive or did not exist
    }


    public void enrollStudentInProgrammeCourseEditions(Student student,  List<CourseEdition> courseEditions){

        for (CourseEdition courseEdition : courseEditions) {
            Optional<CourseEditionEnrollment> existingEnrollment = findByStudentAndEdition(student, courseEdition);
            if (existingEnrollment.isPresent()) {
                throw new IllegalStateException("This course edition enrollment is already in the list.");
            }
            enrollStudentInACourseEdition(student, courseEdition);
        }
    }
}


