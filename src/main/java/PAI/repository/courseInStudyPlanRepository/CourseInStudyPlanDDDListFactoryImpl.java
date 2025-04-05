package PAI.repository.courseInStudyPlanRepository;

import PAI.domain.courseInStudyPlan.CourseInStudyPlan;

import java.util.ArrayList;
import java.util.List;

public class CourseInStudyPlanDDDListFactoryImpl implements ICourseInStudyPlanDDDListFactory {

        public List<CourseInStudyPlan> newArrayList() {
            return new ArrayList<>();
        }
}