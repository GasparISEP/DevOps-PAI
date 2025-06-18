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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyPlanGeneratedID that = (StudyPlanGeneratedID) o;
        return Objects.equals(_uuid, that._uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_uuid);
    }

    @Override
    public String toString() {
        return "StudyPlanGeneratedID{" +
                "_uuid=" + _uuid +
                '}';
    }
}
