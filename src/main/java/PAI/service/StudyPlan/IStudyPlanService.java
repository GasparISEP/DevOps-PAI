package PAI.service.StudyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.StudyPlan;

import java.util.List;
import java.util.Optional;

public interface IStudyPlanService {
    boolean createStudyPlan(ProgrammeID programmeID, Date implementationdate, DurationInYears durationInYears, MaxEcts quantityOfECTS) throws Exception;
    List<StudyPlan> getAllStudyPlans();
    List<StudyPlan> getStudyPlansByProgrammeID(ProgrammeID programmeID);
    StudyPlanID getLatestStudyPlanIDByProgrammeID(ProgrammeID programmeID);
    Optional<StudyPlan> findByID(StudyPlanID id);
}
