package PAI.service.studyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.StudyPlan;
import PAI.dto.studyPlan.RegisterStudyPlanCommand;
import PAI.dto.studyPlan.StudyPlanDTO;

import java.util.List;
import java.util.Optional;

public interface IStudyPlanService {
    StudyPlanDTO createStudyPlan(RegisterStudyPlanCommand studyPlanCommand) throws Exception;
    List<StudyPlan> getAllStudyPlans();
    List<StudyPlan> getStudyPlansByProgrammeID(ProgrammeID programmeID);
    StudyPlanID getLatestStudyPlanIDByProgrammeID(ProgrammeID programmeID);
    Optional<StudyPlan> findByID(StudyPlanID id);
}
