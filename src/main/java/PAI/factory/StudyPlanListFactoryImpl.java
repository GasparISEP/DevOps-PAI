package PAI.factory;

import PAI.domain.CourseInStudyPlan;
import PAI.domain.StudyPlan;

import java.util.ArrayList;
import java.util.List;


public class StudyPlanListFactoryImpl implements IStudyPlanListFactory {

    public List<CourseInStudyPlan> newArrayList() {
        return new ArrayList<>();
    }
}