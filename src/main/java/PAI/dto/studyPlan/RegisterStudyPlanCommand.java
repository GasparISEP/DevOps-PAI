package PAI.dto.studyPlan;

import PAI.VOs.Date;
import PAI.VOs.ProgrammeID;

public class RegisterStudyPlanCommand {

    private final ProgrammeID _programmeID;
    private final Date _startDate;

    public RegisterStudyPlanCommand(ProgrammeID programmeID, Date startDate){
        this._programmeID = programmeID;
        this._startDate = startDate;
    }

    public ProgrammeID getProgrammeId(){
        return _programmeID;
    }

    public Date getStartDate(){
        return _startDate;
    }
}
