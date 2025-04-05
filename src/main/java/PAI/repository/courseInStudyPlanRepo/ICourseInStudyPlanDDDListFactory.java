package PAI.repository.courseInStudyPlanRepo;

import PAI.domain.courseInStudyPlan.CourseInStudyPlan;

import java.util.List;

public interface ICourseInStudyPlanDDDListFactory {

    List<CourseInStudyPlan> newArrayList();
}