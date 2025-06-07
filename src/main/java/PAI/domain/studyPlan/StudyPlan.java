package PAI.domain.studyPlan;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;
import java.util.Objects;

public class StudyPlan implements AggregateRoot<StudyPlanID> {

    private ProgrammeID _programmeID;
    private Date _startDate;
    private DurationInYears _durationInYears;
    private MaxEcts _maxEcts;
    private StudyPlanID _studyPlanID;
    private StudyPlanGeneratedID _studyPlanUUID;

    public StudyPlan(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, MaxEcts quantityOfEcts, StudyPlanID studyPlanID, StudyPlanGeneratedID uuid) {

        this._programmeID = Objects.requireNonNull(programmeID, "Programme ID cannot be null");
        this._startDate = Objects.requireNonNull(implementationDate, "Implementation Date cannot be null");
        this._durationInYears = Objects.requireNonNull(durationInYears, "Duration In Years cannot be null");
        this._maxEcts = Objects.requireNonNull(quantityOfEcts, "Quantity Of ECTs cannot be null");
        this._studyPlanID = Objects.requireNonNull(studyPlanID, "Study Plan ID cannot be null");
        this._studyPlanUUID = Objects.requireNonNull(uuid, "Universally Unique ID cannot be null");
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

    public StudyPlanGeneratedID getGeneratedID () {
        return _studyPlanUUID;
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