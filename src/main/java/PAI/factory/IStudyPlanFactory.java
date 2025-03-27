package PAI.factory;

import PAI.domain.StudyPlan;

public interface IStudyPlanFactory {
    StudyPlan newStudyPlan(ICourseInStudyPlanFactory ICourseInStudyPlanFactory, IStudyPlanListFactory IStudyPlanListFactory, ICourseFactory ICourseFactory);
}
