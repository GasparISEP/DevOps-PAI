package PAI.domain;

import java.time.LocalDate;
import java.util.Objects;

public class CourseEditionEnrollment {

    private Student _student;
    private CourseEdition _courseEdition;
    private LocalDate _enrollmentDate;

    public CourseEditionEnrollment(Student student, CourseEdition courseEdition, LocalDate enrollmentDate) throws IllegalArgumentException {
        validateStudent(student);
        validateCourseEdition(courseEdition);
        validateEnrollmentDate(enrollmentDate);
    }

    private void validateStudent(Student student) throws IllegalArgumentException {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null!");
        }
        this._student = student;
    }

    private void validateCourseEdition(CourseEdition courseEdition) throws IllegalArgumentException {
        if (courseEdition == null) {
            throw new IllegalArgumentException("Course edition cannot be null!");
        }
        this._courseEdition = courseEdition;
    }

    private void validateEnrollmentDate(LocalDate enrollmentDate) throws IllegalArgumentException {
        if (enrollmentDate == null) {
            throw new IllegalArgumentException("Enrollment date cannot be null!");
        }
        if (!enrollmentDate.equals(LocalDate.now())) {
            throw new IllegalArgumentException("Enrollment date must be the current day!");
        }
        this._enrollmentDate = LocalDate.now();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CourseEditionEnrollment that = (CourseEditionEnrollment) obj;
        return Objects.equals(_student, that._student) && Objects.equals(_courseEdition, that._courseEdition) && Objects.equals(_enrollmentDate, that._enrollmentDate);
    }
}
