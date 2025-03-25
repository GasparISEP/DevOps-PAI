package PAI.factory;

import PAI.repository.StudyPlan;

public interface StudyPlanFactory {
    StudyPlan newStudyPlan(CourseInStudyPlanFactory courseInStudyPlanFactory, StudyPlanListFactory studyPlanListFactory, ICourseFactory ICourseFactory);
}
