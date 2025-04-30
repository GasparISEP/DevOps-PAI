package PAI.persistence.datamodel.studyPlan;

import PAI.VOs.DurationInYears;
import PAI.VOs.MaxEcts;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Study_Plan")
public class StudyPlanDataModel {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "programmeName", column = @Column(name = "programme_name", nullable = false)),
            @AttributeOverride(name = "programmeAcronym", column = @Column(name = "programme_acronym", nullable = false))
    })
    private StudyPlanIDDataModel _studyPlanIDDataModel;

    @Column(name = "maxECTS", nullable = false)
    private int _maxECTS;

    @Column(name = "durationInYears", nullable = false)
    private int _durationInYears;

    protected StudyPlanDataModel() {}

    public StudyPlanDataModel (StudyPlanIDDataModel studyPlanIDDataModel, MaxEcts quantityofECTS, DurationInYears durationInYears) {

        if (studyPlanIDDataModel == null) {
            throw new IllegalArgumentException("StudyPlanIDDataModel cannot be null");
        }
        this._studyPlanIDDataModel = studyPlanIDDataModel;

        if (quantityofECTS == null) {
            throw new IllegalArgumentException("MaxECTS cannot be null");
        }
        this._maxECTS = quantityofECTS.getMaxEcts();

        if (durationInYears == null) {
            throw new IllegalArgumentException("DurationInYears cannot be null");
        }
        this._durationInYears = durationInYears.getDurationInYears();
    }

    @Override
    public boolean equals (Object other) {
        if (this == other) return true;
        if (!(other instanceof StudyPlanDataModel)) return false;
        StudyPlanDataModel otherStudyPlanDataModel = (StudyPlanDataModel) other;
        return _studyPlanIDDataModel == otherStudyPlanDataModel._studyPlanIDDataModel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_studyPlanIDDataModel);
    }

    public StudyPlanIDDataModel getStudyPlanIDDataModel() {
        return _studyPlanIDDataModel;
    }

    public int getMaxECTS() {
        return _maxECTS;
    }

    public int getDurationInYears() {
        return _durationInYears;
    }
}