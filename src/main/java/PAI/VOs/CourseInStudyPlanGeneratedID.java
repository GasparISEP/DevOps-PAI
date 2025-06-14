package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;
import java.util.UUID;
import static PAI.utils.ValidationUtils.*;

public final class CourseInStudyPlanGeneratedID implements DomainId {

    private final UUID id;

    public CourseInStudyPlanGeneratedID(UUID id) {
        this.id = validateNotNull(id, "Course In Study Plan Generated ID");
    }

    public static CourseInStudyPlanGeneratedID randomID() {
        return new CourseInStudyPlanGeneratedID(UUID.randomUUID());
    }

    public static CourseInStudyPlanGeneratedID fromString(String idStr) {
        validateNotBlank(idStr, "ID string");
        return new CourseInStudyPlanGeneratedID(UUID.fromString(idStr));
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseInStudyPlanGeneratedID)) return false;
        CourseInStudyPlanGeneratedID that = (CourseInStudyPlanGeneratedID) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
