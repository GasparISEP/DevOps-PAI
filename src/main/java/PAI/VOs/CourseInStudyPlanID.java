package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;
import java.util.UUID;

public class CourseInStudyPlanID implements DomainId {

    private final UUID _courseInStudyPlanId;

    public CourseInStudyPlanID() {
        this._courseInStudyPlanId = UUID.randomUUID();
    }

    public UUID getCourseInStudyPlanId() {
        return _courseInStudyPlanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseInStudyPlanID that)) return false;
        return Objects.equals(_courseInStudyPlanId, that._courseInStudyPlanId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_courseInStudyPlanId);
    }

    @Override
    public String toString() {
        return _courseInStudyPlanId.toString();
    }
}