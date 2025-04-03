package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;
import java.util.UUID;

public class CourseID implements DomainId {

    private final UUID _courseId;

    public CourseID(){
        this._courseId = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof CourseID))
            return false;
        CourseID courseIDTest = (CourseID) object;
        return _courseId.equals(courseIDTest._courseId);
    }

    @Override
    public String toString() {
        return _courseId.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(_courseId);
    }
}