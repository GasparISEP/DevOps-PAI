package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;
import java.util.UUID;

public class CourseEditionEnrolmentGeneratedID {

    private final UUID _courseEditionEnrolmentID;

    public CourseEditionEnrolmentGeneratedID() {

        this._courseEditionEnrolmentID = UUID.randomUUID();
    }

    public CourseEditionEnrolmentGeneratedID(UUID schoolYearID) {
        if (schoolYearID == null){
            throw new IllegalArgumentException("Course Edition Enrolment ID cannot be null");
        }
        this._courseEditionEnrolmentID = schoolYearID;
    }

    public UUID getCourseEditionEnrolmentGeneratedID() {
        return _courseEditionEnrolmentID;
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
