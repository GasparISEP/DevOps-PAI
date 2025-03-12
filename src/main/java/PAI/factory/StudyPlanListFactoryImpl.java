package PAI.factory;

import PAI.domain.CourseInStudyPlan;

import java.util.ArrayList;
import java.util.List;


public class StudyPlanListFactoryImpl implements StudyPlanListFactory {

    public List<CourseInStudyPlan> newArrayList() {
        return new ArrayList<>();
    }
}