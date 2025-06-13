package PAI.domain.courseEditionEnrolment;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

import java.time.LocalDate;
import java.util.Objects;

public class CourseEditionEnrolment implements AggregateRoot<CourseEditionEnrolmentID> {

    private CourseEditionEnrolmentID _courseEditionEnrolmentId;
    private CourseEditionEnrolmentGeneratedID _courseEditionEnrolmentGeneratedID;
    private StudentID _studentID;
    private CourseEditionID _courseEditionID;
    private Date _enrolmentDate;
    private EnrolmentStatus _isActive;

    public CourseEditionEnrolment(StudentID studentID, CourseEditionID courseEditionID) throws IllegalArgumentException {
        validateStudent(studentID);
        validateCourseEdition(courseEditionID);
        this._enrolmentDate= new Date(LocalDate.now());
        this._courseEditionEnrolmentId = new CourseEditionEnrolmentID(studentID,courseEditionID);
        this._isActive= new EnrolmentStatus(true);
        this._courseEditionEnrolmentGeneratedID = new CourseEditionEnrolmentGeneratedID();
    }

    public CourseEditionEnrolment(CourseEditionEnrolmentGeneratedID uuid, StudentID studentID, CourseEditionID courseEditionID, Date enrolmentDate, EnrolmentStatus active) {
        validateStudent(studentID);
        validateCourseEdition(courseEditionID);
        validateGeneratedID(uuid);
        this._enrolmentDate = ((enrolmentDate != null) ? enrolmentDate : Date.now());
        this._courseEditionEnrolmentId = new CourseEditionEnrolmentID(studentID, courseEditionID);
        this._isActive = new EnrolmentStatus(active.isEnrolmentActive());
    }

    private void validateGeneratedID(CourseEditionEnrolmentGeneratedID generatedID) throws IllegalArgumentException {
        if (generatedID == null) {
            throw new IllegalArgumentException("Course Edition Enrolment UUID cannot be null!");
        }
        this._courseEditionEnrolmentGeneratedID = generatedID;
    }

    private void validateStudent(StudentID studentID) throws IllegalArgumentException {
        if (studentID == null) {
            throw new IllegalArgumentException("Student cannot be null!");
        }
        this._studentID = studentID;
    }

    private void validateCourseEdition(CourseEditionID courseEditionID) throws IllegalArgumentException {
        if (courseEditionID == null) {
            throw new IllegalArgumentException("Course edition cannot be null!");
        }
        this._courseEditionID = courseEditionID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CourseEditionEnrolment cee = (CourseEditionEnrolment) obj;
        return Objects.equals(_courseEditionEnrolmentId,cee._courseEditionEnrolmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_studentID, _courseEditionID);
    }

    public boolean hasStudent(StudentID studentID) {
        return _studentID.equals(studentID);
    }

    public boolean hasCourseEdition(CourseEditionID courseEditionID) {
        return _courseEditionID.equals(courseEditionID);
    }

    public CourseEditionEnrolmentGeneratedID getGeneratedID(){return _courseEditionEnrolmentGeneratedID;}

    public StudentID knowStudent() {
        return _studentID;
    }

    public CourseEditionID knowCourseEdition() {
        return _courseEditionID;
    }

    @Override
    public CourseEditionEnrolmentID identity() {
        return _courseEditionEnrolmentId;
    }

    @Override
    public boolean sameAs(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CourseEditionEnrolment cee = (CourseEditionEnrolment) object;
        return _studentID.equals(cee._studentID) &&  _courseEditionID.equals(cee._courseEditionID);
    }

    // Method to check if the enrolment is active
    public boolean isEnrolmentActive() {
        return _isActive.isEnrolmentActive();
    }

    // Method to deactivate the enrolment
    public void deactivateEnrolment() {
        this._isActive = new EnrolmentStatus(false);  // Updating the status to inactive
    }

    public Date getEnrolmentDate() {
        return _enrolmentDate;
    }

    public CourseEditionEnrolmentID getCourseEditionEnrolmentID() {
        return _courseEditionEnrolmentId;
    }
}
