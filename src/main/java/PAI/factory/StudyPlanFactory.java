package PAI.factory;

import PAI.repository.StudyPlan;

public interface StudyPlanFactory {
    StudyPlan newStudyPlan(CourseInStudyPlanFactoryImpl courseInStudyPlanFactory, StudyPlanArrayListFactory studyPlanArrayListFactory, CourseFactory courseFactory);
}
