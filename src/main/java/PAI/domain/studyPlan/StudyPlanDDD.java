package PAI.domain.studyPlan;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

public class StudyPlanDDD implements AggregateRoot<StudyPlanID> {

    private Date _implementationDate;
    private DurationInYears _durationInYears;
    private ProgrammeID _programmeID;
    private StudyPlanID _studyPlanID;
    private QuantEcts _quantityOfEcts;

    public StudyPlanDDD(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, QuantEcts quantityOfEcts) {

        this._implementationDate = implementationDate;
        this._durationInYears = durationInYears;
        this._programmeID = programmeID;
        this._quantityOfEcts = quantityOfEcts;
        this._studyPlanID = new StudyPlanID(programmeID, implementationDate);
    }

    public StudyPlanID getStudyPlanID() {
        return this._studyPlanID;
    }

    public QuantEcts getQuantityOfEcts() {
        return this._quantityOfEcts;
    }

    public ProgrammeID getProgrammeID() {
        return this._programmeID;
    }

    @Override
    public StudyPlanID identity() {
        return _studyPlanID;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (!(other instanceof StudyPlanDDD studyPlan)) return false;
        return this._studyPlanID.equals(studyPlan._studyPlanID);
    }
}