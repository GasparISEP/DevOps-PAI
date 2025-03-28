package PAI.factory;

import PAI.domain.CourseInStudyPlan;
import PAI.domain.CourseInStudyPlan_2;

import java.util.ArrayList;
import java.util.List;

public class CourseInStudyPlanListFactoryImpl_2 implements  ICourseInStudyPlanListFactory_2 {

        public List<CourseInStudyPlan_2> newArrayList() {
            return new ArrayList<>();
        }
}