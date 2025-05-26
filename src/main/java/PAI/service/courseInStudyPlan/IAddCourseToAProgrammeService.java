package PAI.service.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;

public interface IAddCourseToAProgrammeService {

    CourseInStudyPlan addCourseToAProgramme(CourseInStudyPlanCommand courseInStudyPlanCommand) throws Exception;
}