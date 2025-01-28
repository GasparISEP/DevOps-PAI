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

    public Optional<CourseEditionEnrollment> enrollStudentInACourseEdition(Student student, CourseEdition courseEdition, LocalDate enrollmentDate) {

        CourseEditionEnrollment cee1 = new CourseEditionEnrollment(student, courseEdition, enrollmentDate);

        if (isThisEnrollmentAlreadyExists(cee1)){
            return Optional.empty();
        }

        _courseEditionEnrollments.add(cee1);

        return Optional.of(cee1);
    }


    private boolean isThisEnrollmentAlreadyExists (CourseEditionEnrollment courseEditionEnrollment) throws IllegalArgumentException {

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
}
