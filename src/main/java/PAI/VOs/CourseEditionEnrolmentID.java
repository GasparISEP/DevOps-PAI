package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;
import java.util.UUID;


public class CourseEditionEnrolmentID implements DomainId {

    private final UUID _courseEditionEnrolmentId;

    public CourseEditionEnrolmentID() {
        _courseEditionEnrolmentId = UUID.randomUUID();
    }

    public UUID findCeeId() {
        return _courseEditionEnrolmentId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CourseEditionEnrolmentID that = (CourseEditionEnrolmentID) obj;
        return _courseEditionEnrolmentId.equals(that._courseEditionEnrolmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(_courseEditionEnrolmentId);
    }

    @Override
    public String toString() {
        return _courseEditionEnrolmentId.toString();
    }
}
