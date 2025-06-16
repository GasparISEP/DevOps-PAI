package PAI.domain.repositoryInterfaces.studyPlan;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.studyPlan.StudyPlan;

public interface IStudyPlanRepository extends IRepository<StudyPlanID, StudyPlan> {

    StudyPlanID findLatestByProgrammeID(ProgrammeID programmeID);
}