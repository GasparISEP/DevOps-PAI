package PAI.factory;

import PAI.domain.Course;
import java.util.ArrayList;
import java.util.List;

public class CourseListFactoryImpl implements ICourseListFactory {

    public List<Course> createCourseList() {
        return new ArrayList<>();
    }
}
