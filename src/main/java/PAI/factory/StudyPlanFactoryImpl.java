package PAI.factory;

import PAI.repository.StudyPlan;

public class StudyPlanFactoryImpl implements StudyPlanFactory {

    public StudyPlan newStudyPlan(CourseInStudyPlanFactoryImpl courseInStudyPlanFactory, StudyPlanArrayListFactory studyPlanArrayListFactory, CourseFactory courseFactory) {
        return new StudyPlan(courseInStudyPlanFactory, studyPlanArrayListFactory, courseFactory);
    }
}