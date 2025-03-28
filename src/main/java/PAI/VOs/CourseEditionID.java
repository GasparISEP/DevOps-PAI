package PAI.VOs;

import PAI.ddd.DomainId;
import java.util.UUID;

public class CourseEditionID implements DomainId {

    private final UUID _courseEditionId;

    public CourseEditionID() {

        _courseEditionId = UUID.randomUUID();
    }

    @Override
    public String toString() {

        return _courseEditionId.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass())
            return false;
        CourseEditionID courseEditionIDTest = (CourseEditionID) object;
        return _courseEditionId.equals(courseEditionIDTest._courseEditionId);
    }

}
