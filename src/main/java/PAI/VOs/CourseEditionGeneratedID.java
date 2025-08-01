package PAI.VOs;

import java.util.Objects;
import java.util.UUID;

public class CourseEditionGeneratedID {

    private final UUID _courseEditionID;

    public CourseEditionGeneratedID() {

        this._courseEditionID = UUID.randomUUID();
    }

    public CourseEditionGeneratedID(UUID _courseEditionID) {
        if (_courseEditionID == null){
            throw new IllegalArgumentException("Course Edition Generated ID cannot be null");
        }
        this._courseEditionID = _courseEditionID;
    }

    public UUID getCourseEditionGeneratedID() {
        return _courseEditionID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_courseEditionID);
    }

    @Override
    public String toString() {
        return _courseEditionID.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEditionGeneratedID that = (CourseEditionGeneratedID) o;
        return _courseEditionID.equals(that._courseEditionID);
    }
}
