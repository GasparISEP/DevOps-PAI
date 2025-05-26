package PAI.domain.studyPlan;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

public class StudyPlan implements AggregateRoot<StudyPlanID> {

    private Date _startDate;
    private DurationInYears _durationInYears;
    private ProgrammeID _programmeID;
    private StudyPlanID _studyPlanID;
    private MaxEcts _maxEcts;

    public StudyPlan(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, MaxEcts quantityOfEcts, StudyPlanID studyPlanID) {

        if (programmeID == null) {
            throw new IllegalArgumentException("Programme ID cannot be null");
        }
        this._programmeID = programmeID;

        if (implementationDate == null) {
            throw new IllegalArgumentException("Implementation Date cannot be null");
        }
        this._startDate = implementationDate;

        if (durationInYears == null) {
            throw new IllegalArgumentException("Duration In Years cannot be null");
        }
        this._durationInYears = durationInYears;

        if (quantityOfEcts == null) {
            throw new IllegalArgumentException("Quantity Of ECTs cannot be null");
        }
        this._maxEcts = quantityOfEcts;

        if (studyPlanID == null) {
            throw new IllegalArgumentException("Study Plan ID cannot be null");

        }
        this._studyPlanID = studyPlanID;
    }

    public MaxEcts getMaxEcts() {
        return this._maxEcts;
    }

    public ProgrammeID getProgrammeID() {
        return this._programmeID;
    }

    public DurationInYears getDurationInYears() {
        return this._durationInYears;
    }

    public Date getStartDate() {
        return this._startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudyPlan studyPlan = (StudyPlan) o;
        return this._studyPlanID.equals(studyPlan._studyPlanID);
    }

    @Override
    public StudyPlanID identity() {
        return this._studyPlanID;
    }

    @Override
    public boolean sameAs(Object object) {
        if (object instanceof StudyPlan) {
            StudyPlan studyPlan = (StudyPlan) object;

            if (this._programmeID.equals(studyPlan._programmeID) && (this._startDate.equals(studyPlan._startDate)))
                return true;
        }
        return false;
    }
}