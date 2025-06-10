package PAI.VOs;

import PAI.ddd.ValueObject;

import java.util.Objects;
import java.util.UUID;

public class CourseEditionEnrolmentGeneratedID implements ValueObject {

    private final UUID _courseEditionEnrolmentID;

    public CourseEditionEnrolmentGeneratedID() {

        this._courseEditionEnrolmentID = UUID.randomUUID();
    }

    public CourseEditionEnrolmentGeneratedID(UUID schoolYearID) {
        if (schoolYearID == null){
            throw new IllegalArgumentException("Course Edition Enrolment Generated ID cannot be null");
        }
        this._courseEditionEnrolmentID = schoolYearID;
    }

    public UUID getCourseEditionEnrolmentGeneratedID() {
        return _courseEditionEnrolmentID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CourseEditionEnrolmentGeneratedID other = (CourseEditionEnrolmentGeneratedID) obj;
        return Objects.equals(this._courseEditionEnrolmentID, other._courseEditionEnrolmentID);
    }

    public boolean isEquals (UUID ceeGeneratedID) {
        if (ceeGeneratedID == null)
            return false;
        return this._courseEditionEnrolmentID.equals(ceeGeneratedID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_courseEditionEnrolmentID);
    }

    @Override
    public String toString() {
        return _courseEditionEnrolmentID.toString();
    }
}
