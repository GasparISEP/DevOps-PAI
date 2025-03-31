package PAI.repository.courseInStudyPlanRepo;

import PAI.domain.courseInStudyPlan.CourseInStudyPlanDDD;

import java.util.ArrayList;
import java.util.List;

public class CourseInStudyPlanDDDListFactoryImpl implements ICourseInStudyPlanDDDListFactory {

        public List<CourseInStudyPlanDDD> newArrayList() {
            return new ArrayList<>();
        }
}