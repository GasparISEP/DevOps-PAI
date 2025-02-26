package PAI.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseEditionEnrollmentRepository {

    private ArrayList<CourseEditionEnrollment> _courseEditionEnrollments;

    private final CourseEditionEnrollmentFactory _courseEditionEnrollmentFactory;

    //constructor
    public CourseEditionEnrollmentRepository(CourseEditionEnrollmentFactory courseEditionEnrollmentFactory) {

        _courseEditionEnrollments = new ArrayList<>();
        _courseEditionEnrollmentFactory = courseEditionEnrollmentFactory;
    }

    public boolean enrollStudentInACourseEdition(Student student, CourseEdition courseEdition, LocalDate enrollmentDate) {

        CourseEditionEnrollment cee1 = _courseEditionEnrollmentFactory.createCourseEditionEnrollment(student, courseEdition, enrollmentDate);

        if (isEnrollmentAlreadyExists(cee1)){
            return false;
        }

        _courseEditionEnrollments.add(cee1);

        return true;
    }

    //check if this enrollment already exists
    private boolean isEnrollmentAlreadyExists (CourseEditionEnrollment courseEditionEnrollment) throws IllegalArgumentException {

        if (_courseEditionEnrollments.contains(courseEditionEnrollment)) {
            return true;
        }
        return false;
    }

    public boolean isStudentEnrolledInCourseEdition(Student student, CourseEdition courseEdition) {
        for (CourseEditionEnrollment enrollment : _courseEditionEnrollments) {
            if (enrollment.knowStudent().equals(student) && enrollment.knowCourseEdition().equals(courseEdition)) {
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
        for (int i = 0; i < _courseEditionEnrollments.size(); i++) {
            CourseEditionEnrollment enrollment = _courseEditionEnrollments.get(i);
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

    //US28
    // Method to remove the enrollment of a student in a specific course edition
    public boolean removeEnrollment(Student student, CourseEdition courseEdition) {
        // Finds the enrollment for the student and the course edition
        Optional<CourseEditionEnrollment> enrollmentToRemove = findByStudentAndEdition(student, courseEdition);
        if (enrollmentToRemove.isEmpty()) {
            throw new IllegalArgumentException("Enrollment does not exist.");
        }
        _courseEditionEnrollments.remove(enrollmentToRemove.get());
        return true;  // Returns true if the enrollment was successfully removed
    }


    public void enrollStudentInProgrammeCourseEditions(Student student,  List<CourseEdition> courseEditions) {

        for (CourseEdition courseEdition : courseEditions) {
            Optional<CourseEditionEnrollment> existingEnrollment = findByStudentAndEdition(student, courseEdition);
            if (existingEnrollment.isPresent()) {
                throw new IllegalStateException("This course edition enrollment is already in the list.");
            }
            enrollStudentInACourseEdition(student, courseEdition, LocalDate.now());
        }
    }
}


