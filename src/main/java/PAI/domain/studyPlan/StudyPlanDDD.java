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

    public QuantEcts getQuantityOfEcts() {
        return this._quantityOfEcts;
    }

    public ProgrammeID getProgrammeID() {
        return this._programmeID;
    }

    @Override
    public boolean equals (Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudyPlanDDD studyPlanDDD = (StudyPlanDDD) o;
        return this._studyPlanID.equals(studyPlanDDD._studyPlanID);
    }

    @Override
    public StudyPlanID identity() {
        return this._studyPlanID;
    }

    @Override
    public boolean sameAs(Object object) {
    if (object instanceof StudyPlanDDD) {
        StudyPlanDDD studyPlanDDD = (StudyPlanDDD) object;

        if (this._programmeID.equals(studyPlanDDD._programmeID) && (this._implementationDate.equals(studyPlanDDD._implementationDate)) )
            return true;
    }
    return false;
    }
}