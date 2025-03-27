package PAI.factory;

import PAI.domain.StudyPlan;

public class StudyPlanFactoryImpl implements IStudyPlanFactory {

    public StudyPlan newStudyPlan(ICourseInStudyPlanFactory ICourseInStudyPlanFactory, IStudyPlanListFactory IStudyPlanListFactory, ICourseFactory ICourseFactory) {
        return new StudyPlan(ICourseInStudyPlanFactory, IStudyPlanListFactory, ICourseFactory);
    }
}