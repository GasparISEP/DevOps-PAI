package PAI.persistence.mem.courseInStudyPlan;

import PAI.domain.courseInStudyPlan.CourseInStudyPlan;

import java.util.List;

public interface ICourseInStudyPlanListFactory {

    List<CourseInStudyPlan> newArrayList();
}