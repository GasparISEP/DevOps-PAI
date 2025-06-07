package PAI.VOs;

import java.util.UUID;

public class CourseInStudyPlanGeneratedID {

    private final UUID id;

    public CourseInStudyPlanGeneratedID(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Course In Study Plan Generated ID cannot be null");
        }
        this.id = id;
    }

    public static CourseInStudyPlanGeneratedID randomID() {
        return new CourseInStudyPlanGeneratedID(UUID.randomUUID());
    }

    public static CourseInStudyPlanGeneratedID fromString(String idStr) {
        if (idStr == null || idStr.isEmpty()) {
            throw new IllegalArgumentException("ID string cannot be null or empty");
        }
        return new CourseInStudyPlanGeneratedID(UUID.fromString(idStr));
    }

    public UUID getId() {
        return id;
    }
}
