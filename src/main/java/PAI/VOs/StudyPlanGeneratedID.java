package PAI.VOs;

import PAI.ddd.ValueObject;
import java.util.Objects;
import java.util.UUID;

public class StudyPlanGeneratedID implements ValueObject {

    private UUID _uuid;

    public StudyPlanGeneratedID (UUID uuid) {

        _uuid = Objects.requireNonNull(uuid, "Universally Unique ID cannot be null");
    }

    public UUID getUUID () {
        return _uuid;
    }
}
