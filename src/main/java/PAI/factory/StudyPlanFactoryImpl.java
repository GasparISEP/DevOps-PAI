package PAI.factory;

import PAI.repository.StudyPlan;

public interface StudyPlanFactoryImpl {
    StudyPlan newStudyPlan(CourseInStudyPlanFactoryImpl courseInStudyPlanFactory, StudyPlanArrayListFactory studyPlanArrayListFactory, CourseFactory courseFactory);
}
