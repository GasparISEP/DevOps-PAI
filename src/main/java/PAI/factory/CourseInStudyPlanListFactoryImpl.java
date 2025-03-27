package PAI.factory;

import PAI.domain.CourseInStudyPlan;

import java.util.ArrayList;
import java.util.List;

public class CourseInStudyPlanListFactoryImpl implements  ICourseInStudyPlanListFactory{

        public List<CourseInStudyPlan> newArrayList() {
            return new ArrayList<>();
        }
}