package PAI.domain;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;
import PAI.repository.CourseEditionEnrolmentRepository;

import java.time.LocalDate;
import java.util.Objects;

public class CourseEditionEnrolment implements AggregateRoot<CourseEditionEnrolmentID> {

    private CourseEditionEnrolmentID _courseEditionEnrolmentId;
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
        return _courseEditionEnrolmentId.equals(cee._courseEditionEnrolmentId);
    }

    // Method to check if the enrolment is active
    public boolean isEnrolmentActive() {
        return _isActive.isEnrolmentActive();  // Calling the VO method to check enrolment status
    }

    // Method to deactivate the enrolment
    public void deactivateEnrolment() {
        this._isActive = new EnrolmentStatus(false);  // Updating the status to inactive
    }

}
