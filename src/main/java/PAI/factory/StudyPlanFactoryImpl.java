package PAI.factory;

import PAI.factory.CourseFactory;
import PAI.repository.StudyPlan;

public interface StudyPlanFactoryImpl {
    StudyPlan newStudyPlan(CourseInStudyPlanFactory courseInStudyPlanFactory, StudyPlanArrayListFactory studyPlanArrayListFactory, CourseFactory courseFactory);
}
