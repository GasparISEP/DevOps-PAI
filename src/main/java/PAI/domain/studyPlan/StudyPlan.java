package PAI.domain.studyPlan;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

public class StudyPlan implements AggregateRoot<StudyPlanID> {

    private Date _implementationDate;
    private DurationInYears _durationInYears;
    private ProgrammeID _programmeID;
    private StudyPlanID _studyPlanID;
    private MaxEcts _quantityOfEcts;

    public StudyPlan(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, MaxEcts quantityOfEcts) {

        this._implementationDate = implementationDate;
        this._durationInYears = durationInYears;
        this._programmeID = programmeID;
        this._quantityOfEcts = quantityOfEcts;
        this._studyPlanID = new StudyPlanID(programmeID, implementationDate);
    }

    public MaxEcts getQuantityOfEcts() {
        return this._quantityOfEcts;
    }

    public ProgrammeID getProgrammeID() {
        return this._programmeID;
    }

    @Override
    public boolean equals (Object o) {
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

        if (this._programmeID.equals(studyPlan._programmeID) && (this._implementationDate.equals(studyPlan._implementationDate)) )
            return true;
    }
    return false;
    }
}