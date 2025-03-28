package PAI.repository;

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

    public boolean enrolStudentInACourseEdition(Student student, CourseEdition courseEdition) {
        try {
            CourseEditionEnrolment cee1 = _courseEditionEnrolmentFactory.createCourseEditionEnrolment(student, courseEdition);

            return _courseEditionEnrolments.add(cee1);

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStudentEnrolledInCourseEdition(Student student, CourseEdition courseEdition) {
        for (CourseEditionEnrolment enrollment : _courseEditionEnrolments) {
            if (enrollment.knowStudent().equals(student) && enrollment.knowCourseEdition().equals(courseEdition) &&
                    enrollment.isEnrollmentActive()) {
                return true;
            }
        }
        return false;
    }

    public Optional<CourseEditionEnrolment> findByStudentAndEdition(Student student, CourseEdition courseEdition) {
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
    public int numberOfStudentsEnrolledInCourseEdition(CourseEdition courseEdition) throws Exception {

        int count = 0;
        for (CourseEditionEnrolment enrollment : _courseEditionEnrolments) {
            if (enrollment.knowCourseEdition().equals(courseEdition)) {
                count++;
            }
        }
        return count;
    }

    // Method to remove (deactivate) an enrollment
    public boolean removeEnrolment(Student student, CourseEdition courseEdition) {
        Optional<CourseEditionEnrolment> enrollment = findByStudentAndEdition(student, courseEdition);
        if (enrollment.isPresent()) {
            CourseEditionEnrolment cee = enrollment.get();
            if (cee.isEnrollmentActive()) {  // Only deactivates if the enrollment is currently active
                cee.deactivateEnrollment();
                return true; // Returns true indicating that the enrollment was deactivated
            }
        }
        return false; // Returns false if the enrollment was already inactive or did not exist
    }


    public void enrolStudentInProgrammeCourseEditions(Student student, List<CourseEdition> courseEditions){

        for (CourseEdition courseEdition : courseEditions) {
            Optional<CourseEditionEnrolment> existingEnrollment = findByStudentAndEdition(student, courseEdition);
            if (existingEnrollment.isPresent()) {
                throw new IllegalStateException("This course edition enrolment is already in the list.");
            }
            enrolStudentInACourseEdition(student, courseEdition);
        }
    }
}


