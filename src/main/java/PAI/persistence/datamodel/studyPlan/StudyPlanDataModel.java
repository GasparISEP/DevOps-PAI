package PAI.persistence.datamodel.studyPlan;

import PAI.VOs.DurationInYears;
import PAI.VOs.MaxEcts;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Study_Plan")
public class StudyPlanDataModel {

    @EmbeddedId
    private StudyPlanIDDataModel studyPlanIDDataModel;

    @Column(name = "StudyPlanGenerated_ID")
    private UUID uuid;

    @Column(name = "maxECTS", nullable = false)
    private int maxECTS;

    @Column(name = "durationInYears", nullable = false)
    private int durationInYears;

    protected StudyPlanDataModel() {}

    public StudyPlanDataModel (StudyPlanIDDataModel studyPlanIDDataModel, UUID uuid, MaxEcts quantityOfECTS, DurationInYears durationInYears) {

        this.studyPlanIDDataModel = Objects.requireNonNull(studyPlanIDDataModel, "StudyPlanIDDataModel cannot be null");
        this.maxECTS = Objects.requireNonNull(quantityOfECTS, "MaxECTS cannot be null").getMaxEcts();
        this.durationInYears = Objects.requireNonNull(durationInYears, "DurationInYears cannot be null").getDurationInYears();
        this.uuid = Objects.requireNonNull(uuid, "Universally Unique ID cannot be null");
    }

    @Override
    public boolean equals (Object other) {
        if (this == other) return true;
        if (!(other instanceof StudyPlanDataModel)) return false;
        StudyPlanDataModel otherStudyPlanDataModel = (StudyPlanDataModel) other;
        return studyPlanIDDataModel == otherStudyPlanDataModel.studyPlanIDDataModel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studyPlanIDDataModel);
    }

    public UUID getUUID () {
        return this.uuid;
    }

    public StudyPlanIDDataModel getStudyPlanIDDataModel() {
        return studyPlanIDDataModel;
    }

    public int getMaxECTS() {
        return maxECTS;
    }

    public int getDurationInYears() {
        return durationInYears;
    }
}