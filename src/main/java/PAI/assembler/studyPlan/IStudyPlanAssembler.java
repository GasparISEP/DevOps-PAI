package PAI.assembler.studyPlan;

import PAI.domain.studyPlan.StudyPlan;
import PAI.dto.studyPlan.RegisterStudyPlanCommand;
import PAI.dto.studyPlan.StudyPlanDTO;
import PAI.dto.studyPlan.StudyPlanResponseDTO;

import java.time.LocalDate;

public interface IStudyPlanAssembler {
    RegisterStudyPlanCommand toCommand(String programmeAcronym, LocalDate startDate);
    StudyPlanDTO toDTO(StudyPlan studyPlan);
    StudyPlanResponseDTO toResponseDTO(StudyPlanDTO studyPlanDTO);
}
