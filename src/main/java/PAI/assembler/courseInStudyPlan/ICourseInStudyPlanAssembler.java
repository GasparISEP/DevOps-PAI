package PAI.assembler.courseInStudyPlan;

import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanRequestDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;

public interface ICourseInStudyPlanAssembler {

    CourseInStudyPlanCommand toCommand(CourseInStudyPlanRequestDTO request) throws Exception;

    public CourseInStudyPlanResponseDTO toDTO(CourseInStudyPlanServiceDTO courseInStudyPlanServiceDTO);

    CourseInStudyPlanResponseDTO toDTOFromEntity(CourseInStudyPlan course);

    CourseInStudyPlanServiceDTO toServiceDTO(CourseInStudyPlan course);
}
