package PAI.repository.courseInStudyPlanRepo;

import PAI.domain.courseInStudyPlan.CourseInStudyPlanDDD;

import java.util.List;

public interface ICourseInStudyPlanDDDListFactory {

    List<CourseInStudyPlanDDD> newArrayList();
}