package PAI.factory;

import PAI.domain.Course;
import java.util.List;

public interface ICourseListFactory {
    List<Course> createCourseList();
}
