package PAI.assembler.studyPlan;

import PAI.VOs.Date;
import PAI.VOs.DurationInYears;
import PAI.VOs.MaxEcts;
import PAI.domain.studyPlan.StudyPlan;
import PAI.dto.studyPlan.StudyPlanResponseDTO;

public class StudyPlanAssemblerImpl implements IStudyPlanAssembler {

    public StudyPlanResponseDTO toDTO(StudyPlan studyPlan){
        Date startDate = studyPlan.getStartDate();
        MaxEcts quantityOfEcts = studyPlan.getQuantityOfEcts();
        DurationInYears durationInYears = studyPlan.getDurationInYears();

        return new StudyPlanResponseDTO(startDate.getLocalDate(), quantityOfEcts.getMaxEcts(), durationInYears.getDurationInYears());
    }
}
