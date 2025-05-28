package PAI.assembler.courseInStudyPlan;

import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;

public interface ICourseInStudyPlanServiceAssembler {

    CourseInStudyPlanServiceDTO toServiceDTO(CourseInStudyPlan course);

}
