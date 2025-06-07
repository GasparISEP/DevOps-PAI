package PAI.VOs;

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

}
