package PAI.VOs;

import java.util.Objects;
import java.util.UUID;

public class CourseInStudyPlanId {

    private final UUID _CourseInStudyPlanId;

    public CourseInStudyPlanId() {
        this._CourseInStudyPlanId = UUID.randomUUID();
    }

    public UUID getCourseInStudyPlanId() {
        return _CourseInStudyPlanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseInStudyPlanId that = (CourseInStudyPlanId) o;
        return _CourseInStudyPlanId.equals(that._CourseInStudyPlanId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_CourseInStudyPlanId);
    }

    @Override
    public String toString() {
        return _CourseInStudyPlanId.toString();
    }
}