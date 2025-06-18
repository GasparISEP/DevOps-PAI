package PAI.domain.repositoryInterfaces.studyPlan;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.studyPlan.StudyPlan;

import java.util.Optional;

public interface IStudyPlanRepository extends IRepository<StudyPlanID, StudyPlan> {
    Optional<StudyPlan> findByGeneratedID(StudyPlanGeneratedID id);
    StudyPlanID findLatestByProgrammeID(ProgrammeID programmeID);
}