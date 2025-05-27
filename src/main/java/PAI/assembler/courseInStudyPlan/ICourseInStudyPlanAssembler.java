package PAI.assembler.courseInStudyPlan;

import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanRequestDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;

public interface ICourseInStudyPlanAssembler {

    CourseInStudyPlanCommand toCommand(CourseInStudyPlanRequestDTO request) throws Exception;

    CourseInStudyPlanResponseDTO toDTO(CourseInStudyPlan courseInStudyPlan);

}
