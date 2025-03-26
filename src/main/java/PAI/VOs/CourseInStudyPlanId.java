package PAI.VOs;

import PAI.domain.CourseInStudyPlan;

import java.util.Objects;
import java.util.UUID;

public class CourseInStudyPlanId {

    private final UUID _id;

    public CourseInStudyPlanId() {
        this._id = UUID.randomUUID();
    }

    public CourseInStudyPlanId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }
        this._id = id;
    }

    public UUID getId() {
        return _id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseInStudyPlanId that = (CourseInStudyPlanId) o;
        return _id.equals(that._id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }

    @Override
    public String toString() {
        return _id.toString();
    }
}