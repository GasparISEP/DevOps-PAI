package PAI.VOs;

import PAI.ddd.DomainId;
import java.util.UUID;

public class CourseEditionID implements DomainId {

    private final UUID _courseEditionId;

    public CourseEditionID() {

        _courseEditionId = UUID.randomUUID();
    }


}
