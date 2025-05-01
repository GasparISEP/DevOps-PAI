package PAI.persistence.datamodel.studyPlan;

import PAI.VOs.DurationInYears;
import PAI.VOs.MaxEcts;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Study_Plan")
public class StudyPlanDataModel {

    @EmbeddedId
    private StudyPlanIDDataModel studyPlanIDDataModel;

    @Column(name = "maxECTS", nullable = false)
    private int maxECTS;

    @Column(name = "durationInYears", nullable = false)
    private int durationInYears;

    protected StudyPlanDataModel() {}

    public StudyPlanDataModel (StudyPlanIDDataModel studyPlanIDDataModel, MaxEcts quantityofECTS, DurationInYears durationInYears) {

        if (studyPlanIDDataModel == null) {
            throw new IllegalArgumentException("StudyPlanIDDataModel cannot be null");
        }
        this.studyPlanIDDataModel = studyPlanIDDataModel;

        if (quantityofECTS == null) {
            throw new IllegalArgumentException("MaxECTS cannot be null");
        }
        this.maxECTS = quantityofECTS.getMaxEcts();

        if (durationInYears == null) {
            throw new IllegalArgumentException("DurationInYears cannot be null");
        }
        this.durationInYears = durationInYears.getDurationInYears();
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