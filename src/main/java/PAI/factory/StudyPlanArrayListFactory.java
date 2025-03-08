package PAI.factory;

import PAI.domain.CourseInStudyPlan;

import java.util.ArrayList;
import java.util.List;

public class StudyPlanArrayListFactory implements StudyPlanArrayListFactoryImpl {

    public List<CourseInStudyPlan> newArrayList() {
        return new ArrayList<>();
    }
}