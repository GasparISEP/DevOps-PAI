package PAI.persistence.mem.courseInStudyPlan;

import PAI.domain.courseInStudyPlan.CourseInStudyPlan;

import java.util.ArrayList;
import java.util.List;

public class CourseInStudyPlanListFactoryImpl implements ICourseInStudyPlanListFactory {

        public List<CourseInStudyPlan> newArrayList() {
            return new ArrayList<>();
        }
}