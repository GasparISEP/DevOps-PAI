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

    public UUID getId() {
        return id;
    }
}
