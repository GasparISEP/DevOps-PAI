package PAI.domain.studyPlan;

import PAI.VOs.Date;
import PAI.VOs.DurationInYears;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudyPlanID;

public class StudyPlanDDD {

    private Date _implementationDate;
    private Date _endDate;
    private DurationInYears _durationInYears;
    private ProgrammeID _programmeID;
    private StudyPlanID _studyPlanID;

    public StudyPlanDDD(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears) {

        this._implementationDate = implementationDate;
        this._durationInYears = durationInYears;
        this._programmeID = programmeID;
        this._studyPlanID = new StudyPlanID();
    }

    public StudyPlanID getStudyPlanID() {
        return this._studyPlanID;
    }

    public ProgrammeID getProgrammeID() {
        return this._programmeID;
    }
}