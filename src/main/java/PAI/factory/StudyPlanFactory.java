package PAI.factory;

import PAI.repository.StudyPlan;

public class StudyPlanFactory implements StudyPlanFactoryImpl {

    public StudyPlan newStudyPlan(CourseInStudyPlanFactoryImpl courseInStudyPlanFactory, StudyPlanArrayListFactory studyPlanArrayListFactory, CourseFactory courseFactory) {
        return new StudyPlan(courseInStudyPlanFactory, studyPlanArrayListFactory, courseFactory);
    }
}