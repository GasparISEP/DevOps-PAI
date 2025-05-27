package PAI.service.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;

public interface IAddCourseToAProgrammeService {

    CourseInStudyPlanServiceDTO addCourseToAProgramme(CourseInStudyPlanCommand courseInStudyPlanCommand) throws Exception;
}