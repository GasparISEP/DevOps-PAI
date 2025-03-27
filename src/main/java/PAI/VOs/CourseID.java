package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.UUID;

public class CourseID implements DomainId {

    private final UUID _uuid;

    public CourseID(){
        this._uuid = UUID.randomUUID();
    }
}
