package PAI.persistence.datamodel.courseInStudyPlan;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class CourseInStudyPlanGeneratedIDDataModel implements Serializable {

    @Column(name = "course_in_study_plan_generated_id", nullable = false, unique = true)
    private UUID id;

    protected CourseInStudyPlanGeneratedIDDataModel() {
    }

    public CourseInStudyPlanGeneratedIDDataModel(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Generated ID cannot be null");
        }
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseInStudyPlanGeneratedIDDataModel)) return false;
        CourseInStudyPlanGeneratedIDDataModel that = (CourseInStudyPlanGeneratedIDDataModel) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
