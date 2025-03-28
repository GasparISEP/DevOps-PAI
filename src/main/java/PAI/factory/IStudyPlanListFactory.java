package PAI.factory;

import PAI.domain.CourseInStudyPlan;
import java.util.List;

public interface IStudyPlanListFactory {

    List<CourseInStudyPlan> newArrayList();
}