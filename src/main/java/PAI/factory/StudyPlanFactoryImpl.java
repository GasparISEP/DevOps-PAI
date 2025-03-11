package PAI.factory;

import PAI.repository.StudyPlan;

public class StudyPlanFactoryImpl implements StudyPlanFactory {

    public StudyPlan newStudyPlan(CourseInStudyPlanFactory courseInStudyPlanFactory, StudyPlanListFactory studyPlanListFactory, CourseFactory courseFactory) {
        return new StudyPlan(courseInStudyPlanFactory, studyPlanListFactory, courseFactory);
    }
}