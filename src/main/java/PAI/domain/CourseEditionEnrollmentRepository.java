package PAI.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class CourseEditionEnrollmentRepository {

    private ArrayList<CourseEditionEnrollment> _courseEditionEnrollments;

    //constructor
    public CourseEditionEnrollmentRepository() {

        _courseEditionEnrollments = new ArrayList<>();
    }

    public boolean enrollStudentInACourseEdition(Student student, CourseEdition courseEdition, LocalDate enrollmentDate) {

        CourseEditionEnrollment cee1 = new CourseEditionEnrollment(student, courseEdition, enrollmentDate);

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
        return _courseEditionEnrollments.stream()
                .anyMatch(enrollmentStudentCE -> enrollmentStudentCE.knowStudent().equals(student)
                && enrollmentStudentCE.knowCourseEdition().equals(courseEdition));
    }

    //US17
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
    public int numberOfStudentsEnrolledInCourseEdition(CourseEdition courseEdition) {

        int count = 0;
        for (int i = 0; i < _courseEditionEnrollments.size(); i++) {
            CourseEditionEnrollment enrollment = _courseEditionEnrollments.get(i);
            if (enrollment.knowCourseEdition().equals(courseEdition)) {
                count++;
            }
        }
        return count;
    }
}
