package PAI.factory;

import PAI.factory.CourseFactory;
import PAI.repository.StudyPlan;

public class StudyPlanFactory implements StudyPlanFactoryImpl {

    public StudyPlan newStudyPlan(CourseInStudyPlanFactory courseInStudyPlanFactory, StudyPlanArrayListFactory studyPlanArrayListFactory, CourseFactory courseFactory) {
        return new StudyPlan(courseInStudyPlanFactory, studyPlanArrayListFactory, courseFactory);
    }
}