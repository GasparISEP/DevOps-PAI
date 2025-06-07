package PAI.VOs;

import java.util.Objects;
import java.util.UUID;

public class StudyPlanGeneratedID {

    private UUID _uuid;

    public StudyPlanGeneratedID (UUID uuid) {

        Objects.requireNonNull(uuid, "Universally Unique ID cannot be null");

        _uuid = uuid;
    }

    public UUID getUUID () {
        return _uuid;
    }
}
