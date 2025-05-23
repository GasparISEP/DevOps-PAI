package PAI.assembler.studyPlan;

import PAI.domain.studyPlan.StudyPlan;
import PAI.dto.studyPlan.StudyPlanResponseDTO;

public interface IStudyPlanAssembler {
    StudyPlanResponseDTO toDTO(StudyPlan studyPlan);
}
