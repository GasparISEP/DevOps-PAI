package PAI.domain;

import java.time.LocalDate;
import java.util.Objects;

public class CourseEditionEnrollment {

    private Student _student;
    private CourseEdition _courseEdition;
    private LocalDate _enrollmentDate;
    private boolean _isActive;

    public CourseEditionEnrollment(Student student, CourseEdition courseEdition) throws IllegalArgumentException {
        validateStudent(student);
        validateCourseEdition(courseEdition);
        this._enrollmentDate=LocalDate.now();
        this._isActive=true;
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


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CourseEditionEnrollment that = (CourseEditionEnrollment) obj;
        return Objects.equals(_student, that._student) && Objects.equals(_courseEdition, that._courseEdition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_student, _courseEdition);
    }

    public boolean hasStudent(Student student) {
        return _student.equals(student);
    }

    public boolean hasCourseEdition(CourseEdition courseEdition) {
        return _courseEdition.equals(courseEdition);
    }

    public Object knowStudent() {
        return _student;
    }

    public Object knowCourseEdition() {
        return _courseEdition;
    }

    public boolean isEnrollmentActive() {
        if(_isActive){return true;}
        else{return false;
        }
    }

    public void deactivateEnrollment() {
        this._isActive=false;
    }

}
