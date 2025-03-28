package PAI.VOs;

import java.util.Objects;
import java.util.UUID;

public class StudyPlanID {

    private final UUID _studyPlanId;

    public StudyPlanID() {
        this._studyPlanId = UUID.randomUUID();
    }

    public UUID getStudyPlanId() {
        return _studyPlanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudyPlanID that)) return false;
        return Objects.equals(_studyPlanId, that._studyPlanId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_studyPlanId);
    }

    @Override
    public String toString() {
        return _studyPlanId.toString();
    }
}
